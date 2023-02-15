package megatera.makaoGymbackEnd.dtos;

public class LockerRequestDto {
    private String requestMessage;

    public LockerRequestDto() {
    }

    public LockerRequestDto(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getRequestMessage() {
        return requestMessage;
    }
}
