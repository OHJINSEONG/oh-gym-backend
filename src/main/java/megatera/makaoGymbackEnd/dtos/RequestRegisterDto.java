package megatera.makaoGymbackEnd.dtos;

public class RequestRegisterDto {
    private Long receiverId;

    private String type;

    private String context;

    private String senderName;

    public RequestRegisterDto() {
    }

    public RequestRegisterDto(Long receiverId, String type, String context, String senderName) {
        this.receiverId = receiverId;
        this.type = type;
        this.context = context;
        this.senderName = senderName;
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
