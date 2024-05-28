package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.NotificationDto;
import megatera.makaoGymbackEnd.models.Notification;
import megatera.makaoGymbackEnd.repositories.NotificationRepository;
import megatera.makaoGymbackEnd.repositories.SseEmitterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SseEmitterService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private static final Long CACHE_RETENTION_TIME = 5L * 60 * 1000;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final SseEmitterRepository sseEmitterRepository;

    private final NotificationRepository notificationRepository;

    public SseEmitterService(SseEmitterRepository sseEmitterRepository, NotificationRepository notificationRepository) {
        this.sseEmitterRepository = sseEmitterRepository;
        this.notificationRepository = notificationRepository;
    }

    public SseEmitter subscribe(Long userId, String lastEventId) {
        String id = userId + "_" + System.currentTimeMillis();

        SseEmitter emitter = sseEmitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));

        sendToClient(emitter, id, "EventStream Created. [userId=" + userId + "]");

        emitter.onCompletion(() -> scheduleCacheDeletion(id, String.valueOf(userId)));
        emitter.onTimeout(() -> scheduleCacheDeletion(id, String.valueOf(userId)));

        if (!lastEventId.isEmpty()) {
            sendCachedAndMissedNotification(emitter, userId, lastEventId);
        }

        return emitter;
    }

    private void sendCachedAndMissedNotification(SseEmitter emitter, Long userId, String lastEventId) {
        Map<String, Object> cachedEvents = sseEmitterRepository.findAllEventCacheStartWithByMemberId(String.valueOf(userId));
        cachedEvents.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));

        Optional<Notification> missedNotifications = notificationRepository.findByUserIdAndIdGreaterThan(userId, Long.parseLong(lastEventId));
        missedNotifications.stream()
                .filter(notification -> notification.getId() > Long.parseLong(lastEventId))
                .forEach(notification -> sendToClient(emitter, String.valueOf(notification.getId()), notification.toDto()));
    }

    public void send(NotificationDto notificationDto) {
        String id = notificationDto.getId().toString();

        Map<String, SseEmitter> sseEmitters = sseEmitterRepository.findAllEmitterStartWithByMemberId(id);
        sseEmitters.forEach(
                (key, emitter) -> {
                    sseEmitterRepository.saveEventCache(key, notificationDto);
                    sendToClient(emitter, key, notificationDto);
                }
        );
    }

    private void sendToClient(SseEmitter emitter, String id, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data));
        } catch (IOException exception) {
            sseEmitterRepository.deleteById(id);
            throw new RuntimeException("연결 오류!");
        }
    }

    private void scheduleCacheDeletion(String id, String userId) {
        scheduler.schedule(() -> {
            sseEmitterRepository.deleteById(id);
            sseEmitterRepository.deleteAllEmitterStartWithId(userId);
            sseEmitterRepository.deleteAllEventCacheStartWithId(userId);
        }, CACHE_RETENTION_TIME, TimeUnit.MILLISECONDS);
    }
}
