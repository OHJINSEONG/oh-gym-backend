package megatera.makaoGymbackEnd.admin.services;

import megatera.makaoGymbackEnd.dtos.LockerTicketDto;
import megatera.makaoGymbackEnd.models.LockerTicket;
import megatera.makaoGymbackEnd.repositories.LockerTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdminLockerTicketService {
    private final LockerTicketRepository lockerTicketRepository;

    public AdminLockerTicketService(LockerTicketRepository lockerTicketRepository) {
        this.lockerTicketRepository = lockerTicketRepository;
    }

    public LockerTicketDto findByUserId(Long userId) {
        return lockerTicketRepository.findByUserId(userId).map(LockerTicket::toDto).orElse(null);
    }
}
