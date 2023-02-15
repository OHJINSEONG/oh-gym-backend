package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.ScheduleDetailDto;
import megatera.makaoGymbackEnd.dtos.ScheduleDto;
import megatera.makaoGymbackEnd.dtos.WorkDto;
import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.ScheduleService;
import megatera.makaoGymbackEnd.services.WorkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class SchedulesController {
    private final LectureService lectureService;
    private final WorkService workService;
    private final ScheduleService scheduleService;

    public SchedulesController(LectureService lectureService, WorkService workService, ScheduleService scheduleService) {
        this.lectureService = lectureService;
        this.workService = workService;
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ScheduleDto createSchedule(
            @RequestParam("trainerId") Long trainerId,
            @RequestParam("date") String date
    ) {
        List<LectureDto> lectureDtos = lectureService.list(trainerId);

        List<WorkDto> workDtos = workService.findWithDate(trainerId, LocalDate.parse(date));

        return scheduleService.createSchedule(lectureDtos, workDtos);
    }

    @GetMapping("list")
    public List<ScheduleDetailDto> scheduleList(
            @RequestParam("trainerId") Long trainerId
    ) {
        List<LectureDto> lectureDtos = lectureService.list(trainerId);

        List<WorkDto> workDtos = workService.list(trainerId);

        return scheduleService.scheduleList(lectureDtos, workDtos);
    }
}
