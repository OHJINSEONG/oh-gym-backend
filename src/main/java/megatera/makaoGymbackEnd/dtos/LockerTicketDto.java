package megatera.makaoGymbackEnd.dtos;

public class LockerTicketDto {
    private Long id;
    private String startDate;
    private String endDate;
    private String status;
    private Long useOfDate;

    public LockerTicketDto() {
    }

    public LockerTicketDto(
            Long id,
            Long useOfDate,
            String status) {
        this.useOfDate = useOfDate;
        this.id = id;
        this.status = status;
    }

    public LockerTicketDto(Long id, String startDate, String endDate, String status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUseOfDate() {
        return useOfDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }
}
