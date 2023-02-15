package megatera.makaoGymbackEnd.dtos;

public class LockerDetailDto {
    private LockerDto locker;
    private UserDto user;
    private LockerTicketDto lockerTicket;

    public LockerDetailDto() {
    }

    public LockerDetailDto(LockerDto locker, UserDto user, LockerTicketDto lockerTicket) {
        this.locker = locker;
        this.user = user;
        this.lockerTicket = lockerTicket;
    }

    public LockerDto getLocker() {
        return locker;
    }

    public UserDto getUser() {
        return user;
    }

    public LockerTicketDto getLockerTicket() {
        return lockerTicket;
    }
}
