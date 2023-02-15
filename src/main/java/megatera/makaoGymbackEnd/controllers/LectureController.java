package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.ErrorDto;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.RequestErrorDto;
import megatera.makaoGymbackEnd.exceptions.RequestFailed;
import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.PtTicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
public class LectureController {
    private final LectureService lectureService;
    private final PtTicketService ptTicketService;

    public LectureController(LectureService lectureService,
                             PtTicketService ptTicketService) {
        this.lectureService = lectureService;
        this.ptTicketService = ptTicketService;
    }


    @DeleteMapping("{lectureId}")
    public void delete(
            @PathVariable Long lectureId,
            @RequestAttribute("userId") Long userId
    ) {
        LectureDto lectureDto = lectureService.find(lectureId);

        if (lectureDto.getStatus().equals("APPROVE")) {
            ptTicketService.cancelPt(userId);
        }

        lectureService.delete(lectureId);
    }

    @ExceptionHandler(RequestFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto requestFailed(RequestFailed requestFailed) {
        return new RequestErrorDto(requestFailed.getCode(), requestFailed.getMessage());
    }
}
