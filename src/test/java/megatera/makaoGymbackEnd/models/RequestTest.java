package megatera.makaoGymbackEnd.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestTest {
    @Test
    void setContext() {
        Request request = Request.fake("2022-12-09T09:00");
        String senderName = "오진욱";

        request.setContext(senderName);

        assertThat(request.requestMessage().value()).isEqualTo("오진욱님 2022년 12월 09일 09시에 피티 등록 요청.");
    }

    @Test
    void toChecked() {
        Request request = Request.fake("2022-12-09T09:00");
        String senderName = "오진욱";

        request.setContext(senderName);

        request.toCreated();

        assertThat(request.status().value()).isEqualTo("CREATED");

        request.toChecked();

        assertThat(request.status().value()).isEqualTo("CHECKED");
    }

    @Test
    void toDeleted() {
        Request request = Request.fake("2022-12-09T09:00");
        String senderName = "오진욱";

        request.setContext(senderName);

        request.toCreated();

        assertThat(request.status().value()).isEqualTo("CREATED");

        request.toDeleted();

        assertThat(request.status().value()).isEqualTo("DELETED");
    }
}
