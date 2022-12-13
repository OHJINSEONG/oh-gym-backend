package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.dtos.WorkDto;
import megatera.makaoGymbackEnd.models.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work,Long> {
    List<Work> findAllByTrainerId(Long trainerId);

    List<WorkDto> findAllByDate(String date);
}
