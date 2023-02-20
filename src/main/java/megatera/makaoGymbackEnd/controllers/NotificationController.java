package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.NotificationDto;
import megatera.makaoGymbackEnd.services.NotificationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

//    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public SseEmitter connect(
//            @RequestAttribute("userId") Long userId,
//            @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId
//    ) {
//        return notificationService.subscribe(userId, lastEventId);
//    }

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
}
