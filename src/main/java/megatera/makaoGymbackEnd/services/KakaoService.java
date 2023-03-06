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
    public String getAccessToken(String code) {
        String accessToken = "";

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
            sb.append("&redirect_uri=https://ooh-gym.fly.dev/auth/kakao/callback");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println(""+responseCode);
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonElement element = JsonParser.parseString(result);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();

            System.out.println("accessToken : " + accessToken);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
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

            if(email == null){
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
