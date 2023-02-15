package megatera.makaoGymbackEnd.dtos;

public class ChattingRoomRegisterDto {
    private Long trainerId;

    public ChattingRoomRegisterDto() {
    }

    public ChattingRoomRegisterDto( Long trainerId) {
        this.trainerId = trainerId;
    }

    public Long getTrainerId() {
        return trainerId;
    }
}
