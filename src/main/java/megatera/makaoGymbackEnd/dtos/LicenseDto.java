package megatera.makaoGymbackEnd.dtos;

public class LicenseDto {
    private Long id;
    private Long ptTimes;
    private Long periodOfUse;
    private String status;

    public LicenseDto(Long id, Long ptTimes, Long periodOfUse, String status) {
        this.id = id;
        this.ptTimes = ptTimes;
        this.periodOfUse = periodOfUse;
        this.status = status;
    }

    public Long getId() {
        return id;
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
