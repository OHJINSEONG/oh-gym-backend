package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Lecture;
import megatera.makaoGymbackEnd.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
