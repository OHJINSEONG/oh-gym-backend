package megatera.makaoGymbackEnd.models;


import megatera.makaoGymbackEnd.dtos.WorkDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
public class Work {
    @Id
    @GeneratedValue
    private Long id;

    private Long trainerId;

    private String status;

    private String date;

    private String startTime;

    private String endTime;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Work() {
    }

    public Work(Long trainerId, String startTime, String endTime) {
        this.trainerId = trainerId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Work fake(Long trainerId) {
        String startTime = "11:00";
        String endTime = "15:00";

        return new Work(trainerId, startTime, endTime);
    }

    public WorkDto toDto() {
        return new WorkDto(id, status, date, startTime, endTime);
    }

    public String date() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Work.class) &&
                this.id.equals(((Work) other).id);
    }

    public void setStatusCreated() {
        this.status = "CREATED";
    }

    public void setDate(Calendar calendar, int dayOfWeek, int startYear, int startMonth, int startDate) {
        int week = 7;

        calendar.set(Calendar.YEAR, startYear);
        calendar.set(Calendar.MONTH, startMonth - 1);
        calendar.set(Calendar.DATE, startDate);

        if (calendar.get(Calendar.DAY_OF_WEEK) < dayOfWeek) {
            calendar.set(Calendar.DATE, (dayOfWeek - calendar.get(Calendar.DAY_OF_WEEK)) + startDate);
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) > dayOfWeek) {
            calendar.set(Calendar.DATE, ((week + dayOfWeek) - calendar.get(Calendar.DAY_OF_WEEK)) + startDate);
        }

        this.date = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
    }
}
