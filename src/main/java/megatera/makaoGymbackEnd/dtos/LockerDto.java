package megatera.makaoGymbackEnd.dtos;

public class LockerDto {
    private Long id;
    private Long lockerNumber;
    private String status;

    public LockerDto(Long id, Long lockerNumber, String status) {
        this.id = id;
        this.lockerNumber = lockerNumber;
        this.status = status;
    }

    public LockerDto() {

    }

    public Long getId() {
        return id;
    }

    public Long getLockerNumber() {
        return lockerNumber;
    }

    public String getStatus() {
        return status;
    }
}
