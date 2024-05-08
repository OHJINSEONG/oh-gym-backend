package megatera.makaoGymbackEnd.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import megatera.makaoGymbackEnd.dtos.NotificationDto;
import megatera.makaoGymbackEnd.models.Notification;
import megatera.makaoGymbackEnd.repositories.NotificationRepository;
import megatera.makaoGymbackEnd.repositories.SseEmitterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SseEmitterService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final SseEmitterRepository sseEmitterRepository;

    public SseEmitterService(
            SseEmitterRepository sseEmitterRepository
    ) {
        this.sseEmitterRepository = sseEmitterRepository;
    }

    public SseEmitter subscribe(Long userId, String lastEventId) {
        String id = userId + "_" + System.currentTimeMillis();

        SseEmitter emitter = sseEmitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));

        emitter.onCompletion(() -> sseEmitterRepository.deleteById(id));
        emitter.onTimeout(() -> sseEmitterRepository.deleteById(id));

        sendToClient(emitter, id, "EventStream Created. [userId=" + userId + "]");

        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = sseEmitterRepository.findAllEventCacheStartWithByMemberId(String.valueOf(userId));
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
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

    public void send(Long userId, String type, String content) {
        Notification notification = new Notification(userId, content, type);
        String id = String.valueOf(notification.getId());

        Map<String, SseEmitter> sseEmitters = sseEmitterRepository.findAllEmitterStartWithByMemberId(id);
        sseEmitters.forEach(
                (key, emitter) -> {
                    sseEmitterRepository.saveEventCache(key, notification);
                    sendToClient(emitter, key, notification.toDto());
                }
        );
    }
}
