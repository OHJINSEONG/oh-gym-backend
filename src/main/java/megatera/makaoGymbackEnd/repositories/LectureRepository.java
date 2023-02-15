package megatera.makaoGymbackEnd.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import megatera.makaoGymbackEnd.models.Lecture;
import megatera.makaoGymbackEnd.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findAllByTrainerId(Long trainerId);

    List<Lecture> findAllByUserId(Long userId);

    Optional<Lecture> findByDateAndTimeAndTrainerId(LocalDate date, LocalTime time, Long trainerId);

    Optional<Lecture> findByDateAndUserId(LocalDate date, Long userId);
}
