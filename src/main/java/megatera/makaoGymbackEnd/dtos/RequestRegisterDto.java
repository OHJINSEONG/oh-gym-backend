package megatera.makaoGymbackEnd.dtos;

public class RequestRegisterDto {
    private Long senderId;

    private Long receiverId;

    private String type;

    private String context;

    private String senderName;

    public RequestRegisterDto(Long senderId, Long receiverId, String type, String context, String senderName) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.type = type;
        this.context = context;
        this.senderName = senderName;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public String getType() {
        return type;
    }

    public String getContext() {
        return context;
    }

    public String getSenderName() {
        return senderName;
    }
}
