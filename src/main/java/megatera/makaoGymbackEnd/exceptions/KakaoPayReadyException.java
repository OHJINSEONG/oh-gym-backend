package megatera.makaoGymbackEnd.exceptions;

import org.springframework.web.client.RestClientException;

public class KakaoPayReadyException extends RuntimeException {
    public KakaoPayReadyException(RestClientException e) {
        super("gd");
    }
}
