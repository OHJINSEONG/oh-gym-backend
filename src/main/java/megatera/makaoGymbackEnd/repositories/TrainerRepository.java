package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
