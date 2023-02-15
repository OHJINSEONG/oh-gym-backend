package megatera.makaoGymbackEnd.dtos;

public class ChattingRoomDetailDto {
    private ChattingRoomDto chattingRoom;
    private Long count;

    public ChattingRoomDetailDto() {
    }

    public ChattingRoomDetailDto(ChattingRoomDto chattingRoom, Long count) {
        this.chattingRoom = chattingRoom;
        this.count = count;
    }

    public ChattingRoomDto getChattingRoom() {
        return chattingRoom;
    }

    public Long getCount() {
        return count;
    }
}
