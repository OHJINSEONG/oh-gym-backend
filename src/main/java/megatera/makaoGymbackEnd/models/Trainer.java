package megatera.makaoGymbackEnd.models;

import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Trainer {
    @Id
    @GeneratedValue
    private Long id;

    private UserName userName;

    private String name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "start_time"))
    private TaskTime startTime;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "end_time"))
    private TaskTime endTime;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Trainer() {
    }

    public Trainer(UserName userName, String name, TaskTime startTime, TaskTime endTime) {
        this.userName = userName;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Trainer fake(UserName userName) {
        return new Trainer(userName, "오진성", new TaskTime("09:00"), new TaskTime("18:00"));
    }

    public TrainerResultDto toDto() {
        return new TrainerResultDto(id, userName.value(), name, startTime.value(), endTime.value());
    }
}
