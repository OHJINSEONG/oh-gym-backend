package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.DiaryDto;
import megatera.makaoGymbackEnd.dtos.DiaryResultDto;
import megatera.makaoGymbackEnd.dtos.ExerciseResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Entity
public class Diary {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private LocalDate date;

    private String memo;

    private String time;

    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Diary() {
    }

    public Diary(Long userId, LocalDate date, String memo, String time) {
        this.userId = userId;
        this.date = date;
        this.memo = memo;
        this.time = time;
    }

    public Diary(Long id, Long userId, LocalDate date, String memo, String time) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.memo = memo;
        this.time = time;
    }

    public static Diary fake(LocalDate now) {
        Long userId = 2L;
        String memo = "오운완";
        String time = "00:00:00";

        Diary diary = new Diary(1L, userId, now, memo, time);

        diary.create();

        return diary;
    }

    public DiaryDto toDto() {
        return new DiaryDto(id, date.toString(), status.value(), memo, time);
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Diary.class) &&
                this.id.equals(((Diary) other).id);
    }

    public Long id() {
        return id;
    }

    public void complete() {
        this.status.toComplete();
    }

    public void create() {
        this.status = new Status("CREATED");
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DiaryResultDto toResultDto(List<Exercise> exercises, List<Set> sets) {
        List<ExerciseResultDto> exerciseResultDtos = exercises.stream()
                .sorted(Comparator.comparing(Exercise::createAt))
                .filter(exercise -> exercise.diaryId().equals(id))
                .map(exercise -> exercise.toResultDto(sets)).toList();

        return new DiaryResultDto(toDto(), exerciseResultDtos);
    }

    public String time() {
        return time;
    }

    public String memo() {
        return memo;
    }

    public Status status() {
        return status;
    }
}
