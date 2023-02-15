package megatera.makaoGymbackEnd.dtos;

public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private Long ptTimes;
    private Long periodOfUse;
    private String status;

    public UserDto(Long id, String userName, String email, Long ptTimes, Long periodOfUse, String status) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.ptTimes = ptTimes;
        this.periodOfUse = periodOfUse;
        this.status = status;

    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Long getPtTimes() {
        return ptTimes;
    }

    public Long getPeriodOfUse() {
        return periodOfUse;
    }

    public String getStatus() {
        return status;
    }
}
