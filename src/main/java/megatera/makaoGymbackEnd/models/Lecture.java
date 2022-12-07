package megatera.makaoGymbackEnd.models;

import java.time.LocalDateTime;
import java.util.Calendar;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Lecture {
    @Id
    @GeneratedValue
    private Long id;

    private Long orderId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "trainer"))
    private UserName trainer;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "consumer"))
    private UserName consumer;

    private String timeOfPt;

    private String date;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Lecture() {
    }

    public Lecture(Long orderId, UserName trainer, UserName consumer, String timeOfPt) {
        this.orderId = orderId;
        this.trainer = trainer;
        this.consumer = consumer;
        this.timeOfPt = timeOfPt;
    }

    public static Lecture fake(Long orderId) {
        UserName trainer = new UserName("오진욱");
        UserName consumer = new UserName("오진성");
        String timeOfPt = "11:00";

        return new Lecture(orderId, trainer, consumer, timeOfPt);
    }

    public void setDate(Calendar calendar, int dayOfWeek, String ptStartDate) {
        String[] startDates = ptStartDate.split("/");

        int ptFirstYear = Integer.parseInt(startDates[0]);

        int ptFirstMonth = Integer.parseInt(startDates[1]);

        int fDay = Integer.parseInt(startDates[2]);

        int week = 7;

        calendar.set(Calendar.YEAR, ptFirstYear);
        calendar.set(Calendar.MONTH, ptFirstMonth - 1);
        calendar.set(Calendar.DATE, fDay);

        if (calendar.get(Calendar.DAY_OF_WEEK) < dayOfWeek) {
            calendar.set(Calendar.DATE, (dayOfWeek - calendar.get(Calendar.DAY_OF_WEEK)) + fDay);
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) > dayOfWeek) {
            calendar.set(Calendar.DATE, ((week + dayOfWeek) - calendar.get(Calendar.DAY_OF_WEEK)) + fDay);
        }

        this.date = calendar.get(Calendar.YEAR) +
                "/" + (calendar.get(Calendar.MONTH) + 1) +
                "/" + calendar.get(Calendar.DATE);
    }

    public LectureDto toDto() {
        return new LectureDto(id, trainer.value(), consumer.value(), date, timeOfPt);
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
}
