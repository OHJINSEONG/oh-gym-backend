package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.SetDto;
import megatera.makaoGymbackEnd.dtos.SetResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "EXERCISE_SET")
@Entity
public class Set {
    @Id
    @GeneratedValue
    private Long id;

    private Long exerciseId;

    private Weight weight;

    private Long reps;

    private Long setNumber;

    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Set() {
    }

    public Set(Long exerciseId, Weight weight, Long reps, Long setNumber) {
        this.exerciseId = exerciseId;
        this.weight = weight;
        this.reps = reps;
        this.setNumber = setNumber;
    }

    public Set(Long id, Long exerciseId, Weight weight, Long reps, Long setNumber) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.weight = weight;
        this.reps = reps;
        this.setNumber = setNumber;
    }

    public static Set fake(Long reps) {
        Long setNumber = 1L;

        Set set = new Set(1L, 1L, new Weight(70L), reps, setNumber);

        set.created();

        return set;
    }

    public void created() {
        this.status = new Status("CREATED");
    }

    public SetResultDto toDto() {
        return new SetResultDto(id, weight.value(), setNumber, reps, status.value());
    }

    public Long id() {
        return id;
    }

    public void patch(SetDto setDto) {
        this.setNumber = setDto.getSetNumber();
        this.weight = new Weight(setDto.getWeight());
        this.reps = setDto.getReps();
    }

    public void complete() {
        this.status.toComplete();
    }

    public Long exerciseId() {
        return exerciseId;
    }
}
