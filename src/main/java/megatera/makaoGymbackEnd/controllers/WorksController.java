package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.WorkDto;
import megatera.makaoGymbackEnd.dtos.WorkRegisterDto;
import megatera.makaoGymbackEnd.services.WorkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/works")
public class WorksController {
    private final WorkService workService;

    public WorksController(WorkService workService) {
        this.workService = workService;
    }

    @PostMapping
    public List<WorkDto> createWork(
            @RequestBody WorkRegisterDto workRegisterDto
    ) {
        return workService.createWork(
                workRegisterDto.getTrainerId(),
                workRegisterDto.getDate(),
                workRegisterDto.getCountOfWeek(),
                workRegisterDto.getStartTime(),
                workRegisterDto.getEndTime(),
                workRegisterDto.getDayOfWeek()
        );
    }

    @GetMapping("{id}")
    public List<WorkDto> list(
            @PathVariable Long id
    ) {
        return workService.list(id);
    }
}
