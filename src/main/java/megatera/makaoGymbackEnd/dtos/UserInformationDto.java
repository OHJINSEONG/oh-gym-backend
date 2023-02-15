package megatera.makaoGymbackEnd.dtos;

public class UserInformationDto {
    private String email;
    private String userName;

    public UserInformationDto(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
