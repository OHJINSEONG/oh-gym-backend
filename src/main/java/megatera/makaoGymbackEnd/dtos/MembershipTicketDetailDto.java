package megatera.makaoGymbackEnd.dtos;

public class MembershipTicketDetailDto extends TicketDetailDto{
    private Long id;

    private String startDate;

    private String endDate;

    private Long periodOfDate;

    private String status;

    private String trainerImage;

    private String trainerUserName;

    private Long trainerId;

    private String type;

    public MembershipTicketDetailDto() {
    }

    public MembershipTicketDetailDto(
            Long id,
            String startDate,
            String endDate,
            Long periodOfDate,
            String status,
            String trainerImage,
            String trainerUserName,
            Long trainerId,
            String type
    ) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.periodOfDate = periodOfDate;
        this.status = status;
        this.trainerImage = trainerImage;
        this.trainerUserName = trainerUserName;
        this.trainerId = trainerId;
        this.type = type;
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

    public Long getPeriodOfDate() {
        return periodOfDate;
    }

    public String getStatus() {
        return status;
    }

    public String getTrainerImage() {
        return trainerImage;
    }

    public String getTrainerUserName() {
        return trainerUserName;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public String getType() {
        return type;
    }
}
