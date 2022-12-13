package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import megatera.makaoGymbackEnd.services.TrainerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("{id}")
    public TrainerResultDto find(
            @PathVariable("id") Long id
    ) {
        return trainerService.find(id);
    }
}
