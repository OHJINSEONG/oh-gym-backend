package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.LockerTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LockerTicketRepository extends JpaRepository<LockerTicket,Long> {
    Optional<LockerTicket> findByUserId(Long userId);
}
