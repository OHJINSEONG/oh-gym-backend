package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.LockerTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.LockerTicketDto;
import megatera.makaoGymbackEnd.models.Locker;
import megatera.makaoGymbackEnd.models.LockerTicket;
import megatera.makaoGymbackEnd.repositories.LockerRepository;
import megatera.makaoGymbackEnd.repositories.LockerTicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LockerTicketServiceTest {
    private LockerTicketService lockerTicketService;
    private LockerTicketRepository lockerTicketRepository;
    private LockerRepository lockerRepository;

    @BeforeEach
    void setup() {
        lockerRepository = mock(LockerRepository.class);
        lockerTicketRepository = mock(LockerTicketRepository.class);
        lockerTicketService = new LockerTicketService(lockerTicketRepository, lockerRepository);
    }

    @Test
    void use() {
        Long ticketId = 1L;
        String startDate = "2022-12-03";

        given(lockerTicketRepository.getReferenceById(ticketId)).willReturn(
                LockerTicket.fake(1L)
        );

        LockerTicketDto lockerTicketDto = lockerTicketService.use(ticketId, startDate);

        assertThat(lockerTicketDto.getStatus()).isEqualTo("INUSE");
    }

    @Test
    void unUse() {
        Long ticketId = 1L;

        given(lockerTicketRepository.getReferenceById(ticketId)).willReturn(
                LockerTicket.fake(1L)
        );

        LockerTicketDto lockerTicketDto = lockerTicketService.unUse(ticketId);

        assertThat(lockerTicketDto.getStatus()).isEqualTo("UNUSED");
    }

    @Test
    void create() {
        Long userId = 1L;
        Long orderId = 1L;
        Long productId = 1L;
        Long dateOfUse = 90L;

        LockerTicketDto lockerTicketDto = lockerTicketService.create(userId, orderId, dateOfUse, productId);

        assertThat(lockerTicketDto.getUseOfDate()).isEqualTo(90L);
    }

    @Test
    void findByUserId() {
        Long userId = 1L;

        given(lockerTicketRepository.findByUserId(userId)).willReturn(
                Optional.of(LockerTicket.fake(1L))
        );

        given(lockerRepository.findByUserId(userId)).willReturn(
                Optional.of(Locker.fake(1L))
        );

        LockerTicketDetailDto lockerTicketDetailDto = lockerTicketService.findByUserId(userId);

        assertThat(lockerTicketDetailDto.getLocker().getLockerNumber()).isEqualTo(1L);
        assertThat(lockerTicketDetailDto.getLockerTicket().getUseOfDate()).isEqualTo(90L);
    }
}