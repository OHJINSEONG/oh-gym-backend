package megatera.makaoGymbackEnd.dtos;

import megatera.makaoGymbackEnd.models.UserName;

public class UserDto {
    private Long id;
    private UserName userName;
    private String name;
    private Integer ptTimes;
    private Integer periodOfUse;

    public UserDto(Long id, UserName userName, String name, Integer ptTimes, Integer periodOfUse) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.ptTimes = ptTimes;
        this.periodOfUse = periodOfUse;
    }

    public Long getId() {
        return id;
    }

    public UserName getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public Integer getPtTimes() {
        return ptTimes;
    }

    public Integer getPeriodOfUse() {
        return periodOfUse;
    }
}
