package megatera.makaoGymbackEnd.controllers;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.LectureRegisterDto;
import megatera.makaoGymbackEnd.services.LectureService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lectures")
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }
    @GetMapping
    public List<LectureDto> list() {
        return lectureService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<LectureDto> register(
            @RequestBody LectureRegisterDto lectureRegisterDto
            ) {
        List<LectureDto> lectureDtos = lectureService.makeList(
                lectureRegisterDto.getOrderId(),
                lectureRegisterDto.getTrainer(),
                lectureRegisterDto.getConsumer(),
                lectureRegisterDto.getPtTimes(),
                lectureRegisterDto.getTimeOfPt(),
                lectureRegisterDto.getDayOfWeek(),
                lectureRegisterDto.getPtStartDate());

        return lectureDtos;
    }
}
