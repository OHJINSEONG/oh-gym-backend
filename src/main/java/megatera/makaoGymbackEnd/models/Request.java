package megatera.makaoGymbackEnd.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import megatera.makaoGymbackEnd.dtos.RequestResultDto;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;

    private Long senderId;

    private Long receiverId;

    private String context;

    private String message;

    private String status;

    public Request() {
    }

    public Request(Long senderId, Long receiverId, String context) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.context = context;
    }

    public static Request fake(String context) {
        return new Request(1L, 1L, context);
    }

    public String message() {
        return message;
    }

    public String status() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Request.class &&
                Objects.equals(this.id, ((Request) other).id);
    }

    public RequestResultDto toDto() {
        return new RequestResultDto(id, senderId, receiverId, context, message, status);
    }

    public void setContext(String type, String senderName) {
        if (type.equals("requestPt")) {
            String requestDate = LocalDateTime.parse(context).format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시"));

            this.message = senderName + "님 " + requestDate + "에 피티 등록 요청.";
        }
    }

    public void toCreated() {
        this.status = "CREATED";
    }

    public void toChecked() {
        this.status = "CHECKED";
    }

    public void toDeleted() {
        this.status = "DELETED";
    }
}
