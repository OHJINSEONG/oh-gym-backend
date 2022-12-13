package megatera.makaoGymbackEnd.dtos;

public class TrainerResultDto {
    private Long id;
    private String userName;
    private String name;
    private String startTime;
    private String endTime;

    public TrainerResultDto(Long id, String userName, String name, String startTime, String endTime) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
