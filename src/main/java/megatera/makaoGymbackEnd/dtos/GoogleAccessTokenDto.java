package megatera.makaoGymbackEnd.dtos;

public class GoogleAccessTokenDto {
    private String googleAccessToken;

    public GoogleAccessTokenDto() {
    }

    public GoogleAccessTokenDto(String googleAccessToken) {
        this.googleAccessToken = googleAccessToken;
    }

    public String getGoogleAccessToken() {
        return googleAccessToken;
    }
}
