package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.config.GoogleConfig;
import megatera.makaoGymbackEnd.dtos.GoogleInfoResponse;
import megatera.makaoGymbackEnd.dtos.GoogleRequest;
import megatera.makaoGymbackEnd.dtos.GoogleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class GoogleService {
    private final GoogleConfig googleConfig;

    public GoogleService(GoogleConfig googleConfig) {
        this.googleConfig = googleConfig;
    }

    public String getGoogleAccessToken(String authCode) {
        RestTemplate restTemplate = new RestTemplate();

        //토큰을 받위한 객체 생성
        GoogleRequest googleOAuthRequestParam = GoogleRequest.builder()
                .clientId(googleConfig.clientId())
                .clientSecret(googleConfig.clientPassword())
                .code(authCode)
                .redirectUri("https://ooh-gym.fly.dev/api/v1/oauth2/google")
                .grantType("authorization_code")
                .build();

        ResponseEntity<GoogleResponse> apiResponse = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponse.class);

        String googleAccessToken = apiResponse.getBody().getId_token();

        return googleAccessToken;
    }

    public String getGoogleOAuth2RedirectUrl() {
        return "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleConfig.clientId()
                + "&redirect_uri=https://ooh-gym.fly.dev/api/v1/oauth2/google" +
                "&response_type=code&scope=email%20profile%20openid&access_type=offline";
    }

    public ResponseEntity<GoogleInfoResponse> getUser(String googleAccessToken) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> map = new HashMap<>();
        map.put("id_token", googleAccessToken);

        return restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo", map, GoogleInfoResponse.class);
    }
}
