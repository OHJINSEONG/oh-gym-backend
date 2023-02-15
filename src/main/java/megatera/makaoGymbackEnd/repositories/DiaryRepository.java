package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.dtos.DiaryDto;
import megatera.makaoGymbackEnd.models.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary,Long> {
    List<Diary> findAllByUserId(Long userId);

    Optional<Diary> findByDateAndUserId(LocalDate parse, Long userId);
}
