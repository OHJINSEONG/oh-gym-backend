package megatera.makaoGymbackEnd.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import megatera.makaoGymbackEnd.dtos.NotificationDto;
import megatera.makaoGymbackEnd.models.Notification;
import megatera.makaoGymbackEnd.repositories.NotificationRepository;
import megatera.makaoGymbackEnd.repositories.SseEmitterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NotificationService {
    private final SseEmitterRepository sseEmitterRepository;
    private final NotificationRepository notificationRepository;

    public NotificationService(SseEmitterRepository sseEmitterRepository, NotificationRepository notificationRepository) {
        this.sseEmitterRepository = sseEmitterRepository;
        this.notificationRepository = notificationRepository;
    }

//    public SseEmitter subscribe(Long userId, String lastEventId) {
//        String id = userId + "_" + System.currentTimeMillis();
//        SseEmitter emitter = sseEmitterRepository.save(id, new SseEmitter(60L * 1000 * 60));
//
//        emitter.onCompletion(() -> sseEmitterRepository.deleteById(id));
//        emitter.onTimeout(() -> sseEmitterRepository.deleteById(id));
//
//        try {
//            emitter.send(SseEmitter.event()
//                    .id(id)
//                    .name("sse")
//                    .data("EventStream Created. [userId=" + userId + "]"));
//        } catch (IOException exception) {
//            sseEmitterRepository.deleteById(id);
//            throw new RuntimeException(exception.getMessage());
//        }
//
//        return emitter;
//    }

    public void sendNotification(Long userId, String content, String type) {
        Notification notification = new Notification(userId, content, type);
        notification.created();
        notification.setTime();
        notificationRepository.save(notification);

//        Map<String, SseEmitter> sseEmitters = sseEmitterRepository.findUserWithById(String.valueOf(userId));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        sseEmitters.forEach(
//                (key, emitter) -> {
//                    try {
//                        sendToClient(emitter, key, objectMapper.writeValueAsString(notification.toDto()),notification.id());
//                    } catch (JsonProcessingException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//        );
    }

    private void sendToClient(SseEmitter emitter, String id, Object data,Long notificationId) {
        try {
            emitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data));
        } catch (IOException exception) {
            notificationRepository.deleteById(notificationId);
            sseEmitterRepository.deleteById(id);
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<NotificationDto> list(Long userId) {
        return notificationRepository.findAllByUserId(userId).stream().map(Notification::toDto).toList();
    }

    public void delete(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    public List<NotificationDto> patchCheck(Long userId) {
        notificationRepository.findAllByUserId(userId).forEach(Notification::toChecked);

        return notificationRepository.findAllByUserId(userId).stream().map(Notification::toDto).toList();
    }
}
