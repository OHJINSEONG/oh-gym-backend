package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetRepository extends JpaRepository<Set,Long> {
    List<Set> findAllByExerciseId(Long exerciseId);
}
