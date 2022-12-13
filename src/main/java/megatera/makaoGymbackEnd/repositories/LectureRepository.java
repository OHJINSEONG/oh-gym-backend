package megatera.makaoGymbackEnd.repositories;

import java.util.List;
import megatera.makaoGymbackEnd.models.Lecture;
import megatera.makaoGymbackEnd.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findAllByTrainerId(Long trainerId);

    List<Lecture> findAllByUserId(Long userId);

    List<Lecture> findAllByDate(String date);
}
