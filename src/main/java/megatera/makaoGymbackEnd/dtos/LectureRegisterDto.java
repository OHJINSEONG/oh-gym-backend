package megatera.makaoGymbackEnd.dtos;

public class LectureRegisterDto {
    private Long trainerId;
    private String date;

    public LectureRegisterDto(
            Long trainerId,
            String date
    ) {
        this.trainerId = trainerId;
        this.date = date;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public String getDate() {
        return date;
    }
}
