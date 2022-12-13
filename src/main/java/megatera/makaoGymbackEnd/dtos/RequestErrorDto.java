package megatera.makaoGymbackEnd.dtos;

public class RequestErrorDto extends ErrorDto {
    public RequestErrorDto(Integer code, String defaultMessage) {
        super(code, defaultMessage);
    }
}
