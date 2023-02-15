package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByReceiverId(Long receiverId);

    Optional<Request> findByRequestDateTime(LocalDateTime requestDateTime);
}
