package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.LockerDetailDto;
import megatera.makaoGymbackEnd.dtos.LockerDto;
import megatera.makaoGymbackEnd.models.Locker;
import megatera.makaoGymbackEnd.models.LockerTicket;
import megatera.makaoGymbackEnd.models.User;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.repositories.LockerRepository;
import megatera.makaoGymbackEnd.repositories.LockerTicketRepository;
import megatera.makaoGymbackEnd.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LockersServiceTest {
    private LockersService lockersService;
    private LockerRepository lockerRepository;
    private UserRepository userRepository;
    private LockerTicketRepository lockerTicketRepository;

    @BeforeEach
    void setup() {
        lockerRepository = mock(LockerRepository.class);
        userRepository = mock(UserRepository.class);
        lockerTicketRepository = mock(LockerTicketRepository.class);
        lockersService = new LockersService(lockerRepository, userRepository, lockerTicketRepository);
    }

    @Test
    void list() {
        given(lockerRepository.findAll()).willReturn(List.of(
                Locker.fake(1L),
                Locker.fake(2L),
                Locker.fake(3L)
        ));

        List<LockerDto> lockerDtos = lockersService.list();

        assertThat(lockerDtos).hasSize(3);
    }

    @Test
    void patch() {
        Long lockerId = 1L;
        Long userId = 1L;
        String type = "reserve";

        given(lockerRepository.getReferenceById(lockerId)).willReturn(
                Locker.fake(1L)
        );

        LockerDto lockerDto = lockersService.patch(lockerId, userId, type);

        assertThat(lockerDto.getStatus()).isEqualTo("RESERVED");

        LockerDto cancelLockerDto = lockersService.patch(lockerId, userId, "cancel");

        assertThat(cancelLockerDto.getStatus()).isEqualTo("AVAILABLE");
    }

    @Test
    void find() {
        Long lockerId = 1L;
        Long userId = 1L;

        given(lockerRepository.getReferenceById(lockerId)).willReturn(
                Locker.fake(1L)
        );

        given(userRepository.getReferenceById(any())).willReturn(
                User.fake(new UserName("오진성"))
        );

        given(lockerTicketRepository.findByUserId(userId)).willReturn(
                Optional.of(LockerTicket.fake(userId))
        );

        LockerDetailDto lockerDetailDto = lockersService.find(lockerId);

        assertThat(lockerDetailDto.getLocker().getLockerNumber()).isEqualTo(1L);
    }

    @Test
    void adminPatch() {
        Long lockerId = 1L;
        String requestMessage = "APPROVE";

        given(lockerRepository.getReferenceById(lockerId)).willReturn(
                Locker.fake(1L)
        );

        LockerDto lockerDto = lockersService.adminPatch(lockerId,requestMessage);

        assertThat(lockerDto.getStatus()).isEqualTo("INUSE");

        LockerDto rejectedLockerDto = lockersService.adminPatch(lockerId,"REJECT");

        assertThat(rejectedLockerDto.getStatus()).isEqualTo("AVAILABLE");
    }
}
