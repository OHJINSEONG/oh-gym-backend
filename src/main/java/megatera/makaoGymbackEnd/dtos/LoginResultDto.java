package megatera.makaoGymbackEnd.dtos;

public class LoginResultDto {
    private String oauthAccessToken;
    private String accessToken;

    public LoginResultDto(String oauthAccessToken, String accessToken) {
        this.oauthAccessToken = oauthAccessToken;
        this.accessToken = accessToken;
    }

    public String getOauthAccessToken() {
        return oauthAccessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
