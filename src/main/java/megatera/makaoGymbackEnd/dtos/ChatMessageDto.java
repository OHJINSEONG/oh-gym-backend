package megatera.makaoGymbackEnd.dtos;

import java.time.LocalDateTime;

public class ChatMessageDto {
    private Long id;

    private Long roomId;

    private String writer;

    private String message;

    private String status;

    private LocalDateTime time;

    public ChatMessageDto() {
    }

    public ChatMessageDto(Long id, Long roomId, String writer, String message, String status, LocalDateTime time) {
        this.id = id;
        this.roomId = roomId;
        this.writer = writer;
        this.message = message;
        this.status = status;
        this.time = time;
    }

    public Long getId() {
        return id;
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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
