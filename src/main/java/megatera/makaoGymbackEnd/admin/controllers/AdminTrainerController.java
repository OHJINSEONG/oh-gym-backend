package megatera.makaoGymbackEnd.admin.controllers;

import megatera.makaoGymbackEnd.dtos.TrainerManagementDto;
import megatera.makaoGymbackEnd.services.AdminTrainerService;
import megatera.makaoGymbackEnd.services.TrainerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("{trainerId}/use-ticket-users")
    public List<TrainerManagementDto> list(
            @PathVariable Long trainerId
    ) {
        return adminTrainerService.management(trainerId);
    }
}
