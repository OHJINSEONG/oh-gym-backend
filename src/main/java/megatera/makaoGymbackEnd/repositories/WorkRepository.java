package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.dtos.WorkDto;
import megatera.makaoGymbackEnd.models.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work,Long> {
    List<Work> findAllByTrainerId(Long trainerId);

    List<Work> findAllByTrainerIdAndDate(Long trainerId, LocalDate date);
}
