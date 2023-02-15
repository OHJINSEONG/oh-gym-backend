package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.*;
import megatera.makaoGymbackEnd.services.SetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sets")
public class SetsController {
    private final SetService setService;

    public SetsController(SetService setService) {
        this.setService = setService;
    }

    @PostMapping
    public SetResultDto createSet(
            @RequestBody SetRegisterDto setRegisterDto
    ) {
        return setService.create(setRegisterDto.getExerciseId());
    }

    @DeleteMapping("{setId}")
    public void delete(
            @PathVariable Long setId
    ) {
        setService.delete(setId);
    }

    @PatchMapping
    public List<SetResultDto> patch(
            @RequestBody SetPatchDto setPatchDto
    ) {
        return setService.patch(setPatchDto.getExerciseId(),setPatchDto.getSets());
    }

    @PatchMapping("{setId}")
    public SetResultDto complete(
            @PathVariable Long setId
    ) {
        return setService.complete(setId);
    }
}
