package megatera.makaoGymbackEnd.admin.controllers;

import megatera.makaoGymbackEnd.dtos.LectureApproveDto;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.PtTicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-lectures")
public class AdminLectureController {
    private final LectureService lectureService;
    private final PtTicketService ptTicketService;

    public AdminLectureController(LectureService lectureService, PtTicketService ptTicketService) {
        this.lectureService = lectureService;
        this.ptTicketService = ptTicketService;
    }

    @GetMapping("{id}")
    public List<LectureDto> list(
            @PathVariable("id") Long trainerId
    ) {
        return lectureService.list(trainerId);
    }

    @PatchMapping("{lectureId}")
    public LectureDto approve(
            @PathVariable Long lectureId,
            @RequestBody LectureApproveDto lectureApproveDto
    ) {
        ptTicketService.countPt(lectureApproveDto.getUserId());

        return lectureService.approve(lectureId);
    }

    @DeleteMapping("{lectureId}")
    public void delete(
            @PathVariable Long lectureId
    ) {
        lectureService.delete(lectureId);
    }
}
