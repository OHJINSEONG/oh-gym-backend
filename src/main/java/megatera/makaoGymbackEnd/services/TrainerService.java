package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.TrainerManagementDto;
import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.models.PtTicket;
import megatera.makaoGymbackEnd.models.Trainer;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public TrainerResultDto find(Long id) {
        Trainer trainer = trainerRepository.getReferenceById(id);

        return trainer.toDto();
    }

    public List<TrainerResultDto> list() {
        return trainerRepository.findAll().stream().map(Trainer::toDto).toList();
    }
}
