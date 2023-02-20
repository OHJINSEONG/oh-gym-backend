package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.*;
import megatera.makaoGymbackEnd.services.DiaryService;
import megatera.makaoGymbackEnd.services.ExerciseService;
import megatera.makaoGymbackEnd.services.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diarys")
public class DiaryController {
    private final DiaryService diaryService;
    private final ExerciseService exerciseService;

    public DiaryController(DiaryService diaryService, ExerciseService exerciseService) {
        this.diaryService = diaryService;
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public DiaryDto create(
            @RequestBody DiaryRegisterDto diaryRegisterDto,
            @RequestAttribute("userId") Long userId
    ) {
        return diaryService.create(diaryRegisterDto.getDate(), userId);
    }

    @GetMapping("list")
    public List<DiaryResultDto> list(
            @RequestAttribute("userId") Long userId
    ) {
        return diaryService.list(userId);
    }

    @GetMapping
    public DiaryResultDto find(
            @RequestParam("date") String date,
            @RequestAttribute("userId") Long userId
    ) {
        return diaryService.find(date, userId);
    }

    @GetMapping("{diaryId}")
    public DiaryResultDto find(
            @PathVariable Long diaryId
    ) {
        return diaryService.findById(diaryId);
    }

    @DeleteMapping("{diaryId}")
    public void delete(
            @PathVariable Long diaryId
    ) {
        List<ExerciseResultDto> exerciseResultDtos = exerciseService.list(diaryId);

        exerciseResultDtos.forEach(exerciseResultDto -> exerciseService.delete(exerciseResultDto.getExercise().getId()));

        diaryService.delete(diaryId);
    }

    @PatchMapping("{diaryId}")
    public DiaryResultDto complete(
            @PathVariable Long diaryId,
            @RequestBody DiaryPatchDto diaryPatchDto
    ) {
        return diaryService.complete(diaryId, diaryPatchDto.getMemo(), diaryPatchDto.getTime());
    }
}
