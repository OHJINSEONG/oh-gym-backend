package megatera.makaoGymbackEnd.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RequestMessageTest {
    @Test
    void setMessage(){
        RequestMessage requestMessage = new RequestMessage("2022-12-10T11:00","오진성");

        assertThat(requestMessage.value()).isEqualTo("오진성님 2022년 12월 10일 11시에 피티 등록 요청.");
    }
}
