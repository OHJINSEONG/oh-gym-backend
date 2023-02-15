package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.dtos.PtTicketDto;
import megatera.makaoGymbackEnd.models.PtTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PtTicketRepository extends JpaRepository<PtTicket,Long> {
    List<PtTicket> findAllByUserId(Long userId);

    Optional<PtTicket> findByOrderId(Long orderId);

    List<PtTicket> findAllByTrainerId(Long trainerId);
}
