package megatera.makaoGymbackEnd.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import megatera.makaoGymbackEnd.dtos.NotificationDto;
import megatera.makaoGymbackEnd.services.NotificationService;
import megatera.makaoGymbackEnd.services.SseEmitterService;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    private final SseEmitterService sseEmitterService;

    public NotificationController(
            NotificationService notificationService,
            SseEmitterService sseEmitterService
    ) {
        this.notificationService = notificationService;
        this.sseEmitterService = sseEmitterService;
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(
            @RequestAttribute("userId") Long userId,
            @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId
    ) {
        return sseEmitterService.subscribe(userId, lastEventId);
    }

    @GetMapping
    public List<NotificationDto> list(
            @RequestAttribute("userId") Long userId
    ) {
        return notificationService.list(userId);
    }

    @PatchMapping("check")
    public List<NotificationDto> check(
            @RequestAttribute("userId") Long userId
    ) {
        return notificationService.patchCheck(userId);
    }

    @DeleteMapping("{notificationId}")
    public void delete(
            @PathVariable Long notificationId
    ) {
        notificationService.delete(notificationId);
    }

    @SqsListener(value = "${cloud.aws.sqs.queue-name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receive(String message) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            NotificationDto notification = mapper.readValue(message, NotificationDto.class);
            System.out.println("Content: " + notification.getContent());
            sseEmitterService.send(notification.getUserId(), notification.getContent(), notification.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("payload : " + message);
    }
}
