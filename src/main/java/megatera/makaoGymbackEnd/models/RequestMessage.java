package megatera.makaoGymbackEnd.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Embeddable
public class RequestMessage {
    @Column(name = "request_message")
    private String value;

    public RequestMessage() {
    }

    public RequestMessage(String dateTime, String senderName) {
        String requestDate = LocalDateTime.parse(dateTime).format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시"));

        this.value = senderName + "님 " + requestDate + "에 피티 등록 요청.";
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "Status(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == RequestMessage.class &&
                Objects.equals(this.value, ((RequestMessage) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
