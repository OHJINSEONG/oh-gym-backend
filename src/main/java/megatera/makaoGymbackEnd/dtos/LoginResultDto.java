package megatera.makaoGymbackEnd.dtos;

public class LoginResultDto {
    private String kakaoAccessToken;
    private String accessToken;

    public LoginResultDto(String kakaoAccessToken, String accessToken) {
        this.kakaoAccessToken = kakaoAccessToken;
        this.accessToken = accessToken;
    }

    public String getKakaoAccessToken() {
        return kakaoAccessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
