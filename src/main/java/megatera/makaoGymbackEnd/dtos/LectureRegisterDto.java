package megatera.makaoGymbackEnd.dtos;

public class LectureRegisterDto {
    private Long trainerId;
    private Long consumerId;
    private String date;

    public LectureRegisterDto(
            Long trainerId,
            Long consumerId,
            String date
    ) {
        this.trainerId = trainerId;
        this.consumerId = consumerId;
        this.date = date;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public String getDate() {
        return date;
    }
}
