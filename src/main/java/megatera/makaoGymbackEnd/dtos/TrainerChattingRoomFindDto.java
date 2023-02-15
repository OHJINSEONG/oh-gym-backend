package megatera.makaoGymbackEnd.dtos;

public class TrainerChattingRoomFindDto {
    private Long trainerId;

    public TrainerChattingRoomFindDto() {
    }

    public TrainerChattingRoomFindDto(Long trainerId) {
        this.trainerId = trainerId;
    }

    public Long getTrainerId() {
        return trainerId;
    }
}
