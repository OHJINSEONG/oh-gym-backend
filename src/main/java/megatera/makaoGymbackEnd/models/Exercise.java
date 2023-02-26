package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.ExerciseDto;
import megatera.makaoGymbackEnd.dtos.ExerciseResultDto;
import megatera.makaoGymbackEnd.dtos.SetResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Exercise {
    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private Long diaryId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "exercise_name"))
    private Name name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "exercise_type"))
    private Category category;

    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Exercise() {
    }

    public Exercise(Long diaryId, Name name, Category category) {
        this.diaryId = diaryId;
        this.name = name;
        this.category = category;
    }

    public Exercise(Long id, Long diaryId, Name name, Category category) {
        this.id = id;
        this.diaryId = diaryId;
        this.name = name;
        this.category = category;
    }

    public static Exercise fake(Name name) {
        Exercise exercise = new Exercise(1L, 1L, name, new Category("등"));

        exercise.create();

        return exercise;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Exercise.class) &&
                this.id.equals(((Exercise) other).id);
    }

    public void create() {
        this.status = new Status("CREATED");
    }

    public ExerciseDto toDto() {
        return new ExerciseDto(id, name.value(), category.value(), status.value());
    }

    public Long id() {
        return id;
    }

    public void complete() {
        this.status.toComplete();
    }

    public Long diaryId() {
        return diaryId;
    }

    public ExerciseResultDto toResultDto(List<Set> sets) {
        List<SetResultDto> setResultDtos = sets.stream().filter(set -> set.exerciseId().equals(id)).map(Set::toDto).toList();

        return new ExerciseResultDto(toDto(), setResultDtos);
    }

    public LocalDateTime createAt() {
        return createdAt;
    }
}
