package megatera.makaoGymbackEnd.admin.controllers;

import megatera.makaoGymbackEnd.dtos.TrainerDetailDto;
import megatera.makaoGymbackEnd.dtos.TrainerManagementDto;
import megatera.makaoGymbackEnd.dtos.TrainerRegisterDto;
import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import megatera.makaoGymbackEnd.services.AdminTrainerService;
import megatera.makaoGymbackEnd.services.TrainerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-trainers")
public class AdminTrainerController {
    private TrainerService trainerService;
    private AdminTrainerService adminTrainerService;

    public AdminTrainerController(TrainerService trainerService, AdminTrainerService adminTrainerService) {
        this.trainerService = trainerService;
        this.adminTrainerService = adminTrainerService;
    }

    @GetMapping
    public List<TrainerDetailDto> list(
    ) {
        return adminTrainerService.list();
    }

    @GetMapping("{trainerId}/use-ticket-users")
    public List<TrainerManagementDto> trainerMemberList(
            @PathVariable Long trainerId
    ) {
        return adminTrainerService.management(trainerId);
    }

    @PostMapping
    public TrainerResultDto create(
            @RequestBody TrainerRegisterDto trainerRegisterDto
    ) {
        return adminTrainerService.create(trainerRegisterDto.getName(), trainerRegisterDto.getUserName(), trainerRegisterDto.getImage());
    }
}
