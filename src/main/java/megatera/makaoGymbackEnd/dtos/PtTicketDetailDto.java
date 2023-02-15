package megatera.makaoGymbackEnd.dtos;

public class PtTicketDetailDto {
    private Long id;
    private String startDate;
    private String endDate;
    private Long ptTimes;
    private Long periodOfUse;
    private String status;
    private String trainerImage;
    private String trainerUserName;
    private Long trainerId;
    private String type;

    public PtTicketDetailDto() {
    }

    public PtTicketDetailDto(
            Long id,
            String startDate,
            String endDate,
            Long ptTimes,
            Long periodOfUse,
            String status,
            String trainerImage,
            String trainerUserName,
            Long trainerId,
            String type
    ) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ptTimes = ptTimes;
        this.periodOfUse = periodOfUse;
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

    public Long getPtTimes() {
        return ptTimes;
    }

    public Long getPeriodOfUse() {
        return periodOfUse;
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
