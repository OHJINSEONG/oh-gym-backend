package megatera.makaoGymbackEnd.dtos;

public class LockerTicketDetailDto {
    private LockerTicketDto lockerTicket;
    private LockerDto locker;

    public LockerTicketDetailDto() {
    }

    public LockerTicketDetailDto(LockerTicketDto lockerTicket, LockerDto locker) {
        this.lockerTicket = lockerTicket;
        this.locker = locker;
    }

    public LockerTicketDto getLockerTicket() {
        return lockerTicket;
    }

    public LockerDto getLocker() {
        return locker;
    }
}
