package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.TrainerLecturesDto;
import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.TrainerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainers")
public class TrainerLecturesController {
    private final LectureService lectureService;
    private final TrainerService trainerService;

    public TrainerLecturesController(LectureService lectureService, TrainerService trainerService) {
        this.lectureService = lectureService;
        this.trainerService = trainerService;
    }

    @GetMapping("{id}/lectures")
    public TrainerLecturesDto trainerLectures(
            @PathVariable Long id
    ) {
        TrainerResultDto trainerResultDto = trainerService.find(id);

        return lectureService.trainerLectures(id, trainerResultDto.getStartTime(), trainerResultDto.getEndTime());
    }
}
