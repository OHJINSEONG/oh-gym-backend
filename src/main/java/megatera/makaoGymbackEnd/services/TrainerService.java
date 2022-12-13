package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import megatera.makaoGymbackEnd.models.Trainer;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
