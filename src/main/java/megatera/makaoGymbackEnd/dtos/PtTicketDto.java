package megatera.makaoGymbackEnd.dtos;

public class PtTicketDto {
    private Long id;

    private String startDate;

    private String endDate;

    private Long periodOfUse;

    private Long ptTimes;

    private String status;

    public PtTicketDto(Long id, Long periodOfUse, Long ptTimes, String status) {
        this.id = id;
        this.periodOfUse = periodOfUse;
        this.ptTimes = ptTimes;
        this.status = status;
    }

    public PtTicketDto(Long id, String startDate, String endDate, Long ptTimes, String status, Long periodOfUse) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ptTimes = ptTimes;
        this.status = status;
        this.periodOfUse = periodOfUse;
    }

    public Long getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Long getPeriodOfUse() {
        return periodOfUse;
    }

    public Long getPtTimes() {
        return ptTimes;
    }

    public String getStatus() {
        return status;
    }
}
