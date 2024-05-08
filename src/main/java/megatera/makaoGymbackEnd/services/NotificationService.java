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
@Transactional
public class NotificationService {
    @Value("${cloud.aws.end-point.uri}")
    private String sqsEndpoint;

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;

    public NotificationService(
            QueueMessagingTemplate queueMessagingTemplate,
            NotificationRepository notificationRepository,
            ObjectMapper objectMapper,
            SseEmitterRepository sseEmitterRepository
    ) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.notificationRepository = notificationRepository;
        this.objectMapper = objectMapper;
    }

    public void sendNotification(Long userId, String content, String type) {
        Notification notification = new Notification(userId, content, type);
        notification.created();
        notification.setTime();
        notificationRepository.save(notification);

        try {
            String jsonNotification = objectMapper.writeValueAsString(notification.toDto());
            queueMessagingTemplate.convertAndSend(this.sqsEndpoint, jsonNotification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
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
