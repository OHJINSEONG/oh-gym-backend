package megatera.makaoGymbackEnd.dtos;

public class WorkDto {
    private Long id;
    private String status;
    private String date;
    private String startTime;
    private String endTime;

    public WorkDto(Long id, String status, String date, String startTime, String endTime) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
