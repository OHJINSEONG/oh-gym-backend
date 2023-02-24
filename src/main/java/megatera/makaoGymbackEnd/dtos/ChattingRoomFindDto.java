package megatera.makaoGymbackEnd.dtos;

public class ChattingRoomFindDto {
    private Long roomId;
    private Long trainerId;

    public ChattingRoomFindDto() {
    }

    public ChattingRoomFindDto(Long roomId,Long trainerId) {
        this.roomId = roomId;
        this.trainerId = trainerId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getTrainerId() {
        return trainerId;
    }
}
