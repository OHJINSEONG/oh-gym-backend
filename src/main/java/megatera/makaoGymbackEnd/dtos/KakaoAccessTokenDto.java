package megatera.makaoGymbackEnd.dtos;

public class KakaoAccessTokenDto {
    private String kakaoAccessToken;

    public KakaoAccessTokenDto() {
    }

    public KakaoAccessTokenDto(String kakaoAccessToken) {
        this.kakaoAccessToken = kakaoAccessToken;
    }

    public String getKakaoAccessToken() {
        return kakaoAccessToken;
    }
}
