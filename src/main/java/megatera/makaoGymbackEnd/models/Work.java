package megatera.makaoGymbackEnd.models;


import megatera.makaoGymbackEnd.dtos.WorkDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

@Entity
public class Work {
    @Id
    @GeneratedValue
    private Long id;

    private Long trainerId;

    private Status status;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Work() {
    }

    public Work(Long trainerId, LocalTime startTime, LocalTime endTime) {
        this.trainerId = trainerId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Work(Long trainerId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.trainerId = trainerId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Work fake(Long trainerId) {
        Work work = new Work(trainerId, LocalDate.parse("2022-12-10"), LocalTime.parse("11:00"), LocalTime.parse("13:00"));

        work.setStatusCreated();

        return work;
    }

    public WorkDto toDto() {
        return new WorkDto(id, status.value(), date.toString(), startTime.toString(), endTime.toString());
    }

    public LocalDate date() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Work.class) &&
                this.id.equals(((Work) other).id);
    }

    public void setStatusCreated() {
        this.status = new Status("CREATED");
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
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = (calendar.get(Calendar.MONTH) + 1) < 10
                ? "0" + (calendar.get(Calendar.MONTH) + 1)
                : String.valueOf((calendar.get(Calendar.MONTH) + 1));
        String date = calendar.get(Calendar.DATE) < 10
                ? "0" + calendar.get(Calendar.DATE)
                : String.valueOf(calendar.get(Calendar.DATE));

        this.date = LocalDate.parse(year + "-" + month + "-" + date);
    }

    public Status status() {
        return status;
    }
}
