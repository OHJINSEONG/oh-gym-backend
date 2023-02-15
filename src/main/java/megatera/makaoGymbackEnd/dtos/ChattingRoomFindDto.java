package megatera.makaoGymbackEnd.dtos;

public class ChattingRoomFindDto {
    private Long roomId;

    public ChattingRoomFindDto() {
    }

    public ChattingRoomFindDto(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }
}
