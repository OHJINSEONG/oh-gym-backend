package megatera.makaoGymbackEnd.dtos;

public class RequestResultDto {
    private Long id;

    private Long senderId;

    private Long receiverId;

    private String context;

    private String message;

    private String status;

    private Long lectureId;

    public RequestResultDto(
            Long id,
            Long senderId,
            Long receiverId,
            String context,
            String message,
            String status,
            Long lectureId
    ) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.context = context;
        this.message = message;
        this.status = status;
        this.lectureId = lectureId;
    }

    public Long getId() {
        return id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public String getContext() {
        return context;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public Long getLectureId() {
        return lectureId;
    }
}
