package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.LockerTicketDto;
import megatera.makaoGymbackEnd.dtos.MembershipTicketDto;
import megatera.makaoGymbackEnd.dtos.PtTicketDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TicketService {
    private final PtTicketService ptTicketService;
    private final LockerTicketService lockerTicketService;
    private final MembershipTicketService membershipTicketService;

    public TicketService(PtTicketService ptTicketService, LockerTicketService lockerTicketService, MembershipTicketService membershipTicketService) {

        this.ptTicketService = ptTicketService;
        this.lockerTicketService = lockerTicketService;
        this.membershipTicketService = membershipTicketService;
    }

    public void create(Long userId, Long trainerId, Long productId, Long orderId, Long dateOfUse, Long ptTimes, String type) {
        if (type.equals("PT")) {
            ptTicketService.create(userId, trainerId, orderId, dateOfUse, ptTimes, productId);
        }
        if (type.equals("LOCKER")) {
            lockerTicketService.create(userId, orderId, dateOfUse, productId);
        }
        if (type.equals("MEMBERSHIP")) {
            membershipTicketService.create(userId, orderId, dateOfUse, productId);
        }
    }
}
