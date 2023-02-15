package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Chat;
import megatera.makaoGymbackEnd.models.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    List<ChattingRoom> findAllByUserId(Long userId);

    Optional<ChattingRoom> findByTrainerIdAndUserId(Long trainerId, Long userId);

    List<ChattingRoom> findAllByTrainerId(Long trainerId);
}
