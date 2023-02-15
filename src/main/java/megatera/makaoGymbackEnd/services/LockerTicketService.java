package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.LockerDto;
import megatera.makaoGymbackEnd.dtos.LockerTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.LockerTicketDto;
import megatera.makaoGymbackEnd.models.Locker;
import megatera.makaoGymbackEnd.models.LockerTicket;
import megatera.makaoGymbackEnd.models.Period;
import megatera.makaoGymbackEnd.repositories.LockerRepository;
import megatera.makaoGymbackEnd.repositories.LockerTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LockerTicketService {
    private final LockerTicketRepository lockerTicketRepository;
    private final LockerRepository lockerRepository;

    public LockerTicketService(LockerTicketRepository lockerTicketRepository, LockerRepository lockerRepository) {
        this.lockerTicketRepository = lockerTicketRepository;
        this.lockerRepository = lockerRepository;
    }

    public LockerTicketDto use(Long ticketId, String startDate) {
        LockerTicket lockerTicket = lockerTicketRepository.getReferenceById(ticketId);

        lockerTicket.setStartDate(startDate);

        lockerTicket.used();

        return lockerTicket.toDto();
    }

    public LockerTicketDto create(Long userId, Long orderId, Long dateOfUse, Long productId) {
        LockerTicket lockerTicket = new LockerTicket(userId, orderId, new Period(dateOfUse), productId);

        lockerTicket.unUsed();

        lockerTicketRepository.save(lockerTicket);

        return lockerTicket.toDto();
    }

    public LockerTicketDetailDto findByUserId(Long userId) {
        LockerTicketDto lockerTicketDto = lockerTicketRepository.findByUserId(userId).map(LockerTicket::toDto).orElse(null);

        LockerDto lockerDto = lockerRepository.findByUserId(userId).map(Locker::toDto).orElse(null);

        return new LockerTicketDetailDto(lockerTicketDto, lockerDto);
    }

    public LockerTicketDto unUse(Long ticketId) {
        LockerTicket lockerTicket = lockerTicketRepository.getReferenceById(ticketId);

        lockerTicket.unUsed();

        return lockerTicket.toDto();
    }
}
