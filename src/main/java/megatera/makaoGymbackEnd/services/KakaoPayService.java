package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.exceptions.KakaoPayReadyException;
import megatera.makaoGymbackEnd.models.KakaopayApprove;
import megatera.makaoGymbackEnd.models.KakaopayReady;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Transactional
@Service
public class KakaoPayService {
    private KakaopayReady kakaopayReady;

    private KakaopayApprove kakaopayApprove;

    private String orderId = "1000";

    private String userId = "OH-Gym(오진성)";


    public KakaopayReady kakaoPayReady(
            String itemName , Long totalAmount
    ) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "985ab68b5573382497638038d4414fde");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", orderId);
        params.add("partner_user_id", userId);
        params.add("item_name", itemName);
        params.add("total_amount", String.valueOf(totalAmount));
        params.add("tax_free_amount", "1000");
        params.add("quantity", "1");
        params.add("approval_url", "https://ooh-gym.fly.dev/orders/success");
        params.add("cancel_url", "https://ooh-gym.fly.dev/orders/cancel");
        params.add("fail_url", "https://ooh-gym.fly.dev/orders/fail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v1/payment/ready";

        try {
            kakaopayReady = restTemplate.postForObject(new URI(url), body, KakaopayReady.class);
            return kakaopayReady;
        } catch (RestClientException e) {
            throw new KakaoPayReadyException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public KakaopayApprove kakaoPayInfo(String pg_token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "985ab68b5573382497638038d4414fde");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaopayReady.getTid());
        params.add("partner_order_id", orderId);
        params.add("partner_user_id", userId);
        params.add("pg_token", pg_token);

        HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v1/payment/approve";

        try {
            return restTemplate.postForObject(new URI(url), requestBody, KakaopayApprove.class);

        } catch (RestClientException e) {
            throw new KakaoPayReadyException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
