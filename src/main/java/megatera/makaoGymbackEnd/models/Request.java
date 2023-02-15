package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.RequestResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;

    private Long senderId;

    private Long receiverId;

    private Long lectureId;

    private LocalDateTime requestDateTime;

    private RequestMessage requestMessage;

    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Request() {
    }

    public Request(Long senderId, Long receiverId, LocalDateTime requestDateTime, Long lectureId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.requestDateTime = requestDateTime;
        this.lectureId = lectureId;
    }

    public static Request fake(String requestDateTime) {
        Request request =  new Request(1L, 1L, LocalDateTime.parse(requestDateTime), 1L);

        request.toCreated();

        request.setContext("하이");

        return request;
    }

    public Status status() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Request.class &&
                Objects.equals(this.id, ((Request) other).id);
    }

    public RequestResultDto toDto() {
        return new RequestResultDto(id, senderId, receiverId, requestDateTime.toString(), requestMessage.value(), status.value(), lectureId);
    }


    public void toCreated() {
        this.status = new Status("CREATED");
    }

    public void toChecked() {
        this.status.toChecked();
    }

    public void toDeleted() {
        this.status.toDeleted();
    }

    public void setContext(String senderName) {
        this.requestMessage = new RequestMessage(requestDateTime.toString(), senderName);
    }

    public RequestMessage requestMessage() {
        return requestMessage;
    }
}
