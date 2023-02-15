package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByRoomId(Long roomId);
}
