package megatera.makaoGymbackEnd.dtos;

public class ChatMessageRegisterDto {
    private Long roomId;

    private String writer;

    private String message;

    private Long trainerId;

    public ChatMessageRegisterDto() {
    }

    public ChatMessageRegisterDto(Long roomId, String writer, String message,Long trainerId) {
        this.roomId = roomId;
        this.writer = writer;
        this.message = message;
        this.trainerId = trainerId;
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

    public Long getTrainerId() {
        return trainerId;
    }
}
