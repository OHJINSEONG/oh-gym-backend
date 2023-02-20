package megatera.makaoGymbackEnd.admin.controllers;

import megatera.makaoGymbackEnd.dtos.LectureApproveDto;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.NotificationService;
import megatera.makaoGymbackEnd.services.PtTicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-lectures")
public class AdminLectureController {
    private final LectureService lectureService;
    private final PtTicketService ptTicketService;
    private final NotificationService notificationService;

    public AdminLectureController(LectureService lectureService, PtTicketService ptTicketService, NotificationService notificationService) {
        this.lectureService = lectureService;
        this.ptTicketService = ptTicketService;
        this.notificationService = notificationService;
    }

    @GetMapping("{id}")
    public List<LectureDto> list(
            @PathVariable("id") Long trainerId
    ) {
        return lectureService.list(trainerId);
    }

    @PatchMapping("approve/{lectureId}")
    public LectureDto approve(
            @PathVariable Long lectureId,
            @RequestBody LectureApproveDto lectureApproveDto
    ) {
        String context = lectureApproveDto.getMessage().split("\\.")[0] + "이 승인되었습니다.";

        notificationService.sendNotification(lectureApproveDto.getUserId(), context, "PT");

        ptTicketService.countPt(lectureApproveDto.getUserId());

        return lectureService.approve(lectureId);
    }

    @PatchMapping("cancel/{lectureId}")
    public void cancel(
            @PathVariable Long lectureId,
            @RequestBody LectureApproveDto lectureApproveDto
    ) {
        String context = lectureApproveDto.getMessage().split("\\.")[0] + "이 거절되었습니다.";

        notificationService.sendNotification(lectureApproveDto.getUserId(), context, "PT");

        ptTicketService.cancelPt(lectureApproveDto.getUserId());

        lectureService.delete(lectureId);
    }
}
