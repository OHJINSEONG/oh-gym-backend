package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.LockerDetailDto;
import megatera.makaoGymbackEnd.dtos.LockerDto;
import megatera.makaoGymbackEnd.dtos.LockerTicketDto;
import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.models.Locker;
import megatera.makaoGymbackEnd.models.LockerTicket;
import megatera.makaoGymbackEnd.repositories.LockerRepository;
import megatera.makaoGymbackEnd.repositories.LockerTicketRepository;
import megatera.makaoGymbackEnd.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LockersService {
    private final LockerRepository lockerRepository;
    private final UserRepository userRepository;
    private final LockerTicketRepository lockerTicketRepository;

    public LockersService(
            LockerRepository lockerRepository,
            UserRepository userRepository,
            LockerTicketRepository lockerTicketRepository
    ) {
        this.lockerRepository = lockerRepository;
        this.userRepository = userRepository;
        this.lockerTicketRepository = lockerTicketRepository;
    }

    public List<LockerDto> list() {
        return lockerRepository.findAll().stream().map(Locker::toDto).toList();
    }

    public LockerDto patch(Long lockerId, Long userId, String type) {
        Locker locker = lockerRepository.getReferenceById(lockerId);
        Optional<Locker> optionalLocker = lockerRepository.findByUserId(userId);

        if (type.equals("reserve") || optionalLocker.isEmpty()) {
            locker.reserved();
            locker.setUserId(userId);
        }

        if (type.equals("cancel")) {
            locker.inAvailable();
            locker.deleteUserId();
        }

        return locker.toDto();
    }

    public LockerDetailDto find(Long lockerId) {
        Locker locker = lockerRepository.getReferenceById(lockerId);

        UserDto userDto = userRepository.getReferenceById(locker.getUserId()).toDto();

        LockerTicketDto lockerTicketDto = lockerTicketRepository.findByUserId(locker.getUserId())
                .map(LockerTicket::toDto).orElseGet(() -> null);

        return new LockerDetailDto(locker.toDto(), userDto, lockerTicketDto);
    }

    public LockerDto adminPatch(Long lockerId, String requestMessage) {
        Locker locker = lockerRepository.getReferenceById(lockerId);

        if (requestMessage.equals("APPROVE")) {
            locker.inUse();
        }
        if (requestMessage.equals("REJECT")) {
            locker.inAvailable();
            locker.deleteUserId();
        }

        return locker.toDto();
    }
}
