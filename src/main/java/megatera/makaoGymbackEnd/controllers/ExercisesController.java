package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.ExerciseDto;
import megatera.makaoGymbackEnd.dtos.ExerciseRegisterDto;
import megatera.makaoGymbackEnd.dtos.ExerciseResultDto;
import megatera.makaoGymbackEnd.dtos.SetResultDto;
import megatera.makaoGymbackEnd.services.ExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExercisesController {
    private final ExerciseService exerciseService;

    public ExercisesController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ExerciseDto create(
            @RequestBody ExerciseRegisterDto exerciseRegisterDto
    ) {
        return exerciseService.create(
                exerciseRegisterDto.getName(),
                exerciseRegisterDto.getType(),
                exerciseRegisterDto.getDiaryId()
        );
    }

    @GetMapping("{exerciseId}")
    public ExerciseResultDto find(
            @PathVariable Long exerciseId
    ) {
        return exerciseService.find(exerciseId);
    }

    @GetMapping
    public List<ExerciseResultDto> list(
            @RequestParam("diaryId") Long diaryId
    ) {
        return exerciseService.list(diaryId);
    }

    @PatchMapping("{exerciseId}")
    public ExerciseResultDto complete(
            @PathVariable Long exerciseId
    ) {
        return exerciseService.complete(exerciseId);
    }

    @DeleteMapping("{exerciseId}")
    public void delete(
            @PathVariable Long exerciseId
    ) {
        exerciseService.delete(exerciseId);
    }
}
