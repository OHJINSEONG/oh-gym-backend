package megatera.makaoGymbackEnd.services;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import megatera.makaoGymbackEnd.exceptions.InValidEmail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Transactional
@Service
public class KakaoService {
    public HashMap<String, String> getAccessToken(String code) {
        HashMap<String, String> tokenInfo = new HashMap<>();

        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=f99c39ffcdf63597195c1d3678b78fde");

            String redirectUri = "http://localhost:8080/auth/kakao/callback";
            if (System.getenv("ENVIRONMENT") != null && System.getenv("ENVIRONMENT").equals("PRODUCTION")) {
                redirectUri = "https://ooh-gym.fly.dev/auth/kakao/callback";
            }

            sb.append("&redirect_uri=" + redirectUri);
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("" + responseCode);
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonElement element = JsonParser.parseString(result);

            String accessToken = element.getAsJsonObject().get("access_token").getAsString();
            String refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
            long expiresIn = element.getAsJsonObject().get("expires_in").getAsLong();

            tokenInfo.put("accessToken", accessToken);
            tokenInfo.put("refreshToken", refreshToken);
            tokenInfo.put("expiresIn", String.valueOf(expiresIn));

            System.out.println("Access Token: " + tokenInfo.get("accessToken"));
            System.out.println("Refresh Token: " + refreshToken);
            System.out.println("Expires In: " + expiresIn);

            System.out.println("accessToken : " + accessToken);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tokenInfo;
    }

    public HashMap<String, String> getUser(String kakaoAccessToken) {
        HashMap<String, String> userInformation = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer " + kakaoAccessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonElement element = JsonParser.parseString(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();

            System.out.println(email);

            if (email == null) {
                throw new InValidEmail();
            }

            userInformation.put("nickname", nickname);
            userInformation.put("email", email);

            System.out.println("response body : " + userInformation);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInformation;
    }

    public HashMap<String, String> logout(String kakaoAccessToken) {
        HashMap<String, String> userInformation = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v1/user/unlink";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer " + kakaoAccessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonElement element = JsonParser.parseString(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInformation;
    }
}
