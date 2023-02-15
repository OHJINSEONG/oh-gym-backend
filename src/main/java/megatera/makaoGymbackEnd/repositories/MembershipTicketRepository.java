package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.MembershipTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface MembershipTicketRepository extends JpaRepository<MembershipTicket,Long> {
    List<MembershipTicket> findAllByUserId(Long userId);
}
