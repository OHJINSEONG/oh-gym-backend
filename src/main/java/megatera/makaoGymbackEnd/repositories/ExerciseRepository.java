package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
    List<Exercise> findAllByDiaryId(Long diaryId);
}
