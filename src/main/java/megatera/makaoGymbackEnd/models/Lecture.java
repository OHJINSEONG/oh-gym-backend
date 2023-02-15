package megatera.makaoGymbackEnd.models;


import megatera.makaoGymbackEnd.dtos.LectureDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Lecture {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long trainerId;

    private Status status;

    private LocalDate date;

    private LocalTime time;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "consumer_name"))
    private Name consumerName;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Lecture() {
    }

    public Lecture(Long trainerId, Long userId, LocalDate date, LocalTime time, Name consumerName) {
        this.trainerId = trainerId;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.consumerName = consumerName;
    }

    public static Lecture fake(String date) {
        Long trainerId = 1L;
        Long consumerId = 1L;
        String time = "11:00";
        String consumerName = "오진욱";

        Lecture lecture = new Lecture(trainerId, consumerId, LocalDate.parse(date), LocalTime.parse(time), new Name(consumerName));

        lecture.toCreated();

        return lecture;
    }

    private void toCreated() {
        this.status = new Status("CREATED");
    }

    public LectureDto toDto() {
        return new LectureDto(id, consumerName.value(), status.value(), date.toString(), time.toString());
    }

    public LocalDate date() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Lecture.class) &&
                this.id.equals(((Lecture) other).id);
    }

    public void approve() {
        this.status.toApprove();
    }

    public void setStatusReserved() {
        this.status = new Status("RESERVED");
    }

    public Status status() {
        return status;
    }
}
