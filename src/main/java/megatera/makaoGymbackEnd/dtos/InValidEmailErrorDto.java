package megatera.makaoGymbackEnd.dtos;

public class InValidEmailErrorDto extends ErrorDto {
    public InValidEmailErrorDto(String errorMessage) {
        super(2000, errorMessage);
    }
}
