package megatera.makaoGymbackEnd.models;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.*;

import megatera.makaoGymbackEnd.dtos.LectureDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Lecture {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long trainerId;

    private Status status;

    private String date;

    private String time;

    private String consumerName;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Lecture() {
    }

    public Lecture(Long trainerId, Long userId, String date, String time, String consumerName) {
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

        return new Lecture(trainerId, consumerId, date, time, consumerName);
    }

    public LectureDto toDto() {
        return new LectureDto(id, consumerName, status, date, time);
    }

    public String date() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Lecture.class) &&
                this.id.equals(((Lecture) other).id);
    }

    public void setStatusCreated() {
        this.status = "CREATED";
    }
}

// Work 피티 2시 6시  ㄴㅇㄴ