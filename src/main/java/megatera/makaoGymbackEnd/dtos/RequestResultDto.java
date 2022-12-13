package megatera.makaoGymbackEnd.dtos;

public class RequestResultDto {
    private Long id;

    private Long senderId;

    private Long receiverId;

    private String context;

    private String message;

    private String status;

    public RequestResultDto(
            Long id,
            Long senderId,
            Long receiverId,
            String context,
            String message,
            String status
    ) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.context = context;
        this.message = message;
        this.status = status;
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
}
