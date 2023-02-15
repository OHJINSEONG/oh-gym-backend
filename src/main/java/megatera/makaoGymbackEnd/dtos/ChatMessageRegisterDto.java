package megatera.makaoGymbackEnd.dtos;

public class ChatMessageRegisterDto {
    private Long roomId;

    private String writer;

    private String message;

    public ChatMessageRegisterDto() {
    }

    public ChatMessageRegisterDto(Long roomId, String writer, String message) {
        this.roomId = roomId;
        this.writer = writer;
        this.message = message;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getWriter() {
        return writer;
    }

    public String getMessage() {
        return message;
    }
}
