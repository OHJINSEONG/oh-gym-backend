package megatera.makaoGymbackEnd.dtos;

public class MembershipTicketDto {
    private Long id;
    private String startDate;
    private String endDate;
    private String status;
    private Long useOfDate;

    public MembershipTicketDto(
            Long id,
            Long useOfDate,
            String status) {
        this.useOfDate = useOfDate;
        this.id = id;
        this.status = status;
    }

    public MembershipTicketDto(Long id, String startDate, String endDate, String status,Long useOfDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.useOfDate = useOfDate;
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
