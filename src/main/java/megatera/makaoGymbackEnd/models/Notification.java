package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.NotificationDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String content;

    private String type;

    private Status status;

    private String time;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Notification() {
    }

    public Notification(Long userId, String content, String type) {
        this.userId = userId;
        this.content = content;
        this.type = type;
    }

//    public static Notification fake(Long userId) {
//        Long productId = 1L;
//        Long orderId = 1L;
//        Long trainerId = 1L;
//
//        Notification ptTicket = new Notification(userId, trainerId, orderId, new Period(90L), new Count(12L), productId);
//
//        ptTicket.unUsed();
//
//        return ptTicket;
//    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Notification.class &&
                Objects.equals(this.id, ((Notification) other).id);
    }

    public void used() {
        this.status.toUsed();
    }

    public void created() {
        this.status = new Status("CREATED");
    }

    public Status status() {
        return status;
    }

    public NotificationDto toDto() {
        return new NotificationDto(id, userId, content, status.value(), type, time);
    }

    public Long id() {
        return id;
    }

    public void setTime() {
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void toChecked() {
        this.status.toChecked();
    }
}
