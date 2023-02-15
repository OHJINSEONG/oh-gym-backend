package megatera.makaoGymbackEnd.dtos;

import megatera.makaoGymbackEnd.models.Status;

public class SetResultDto {
    private Long id;
    private Long weight;
    private Long setNumber;
    private Long reps;
    private String status;

    public SetResultDto(Long id, Long weight, Long setNumber, Long reps, String status) {
        this.id = id;
        this.weight = weight;
        this.setNumber = setNumber;
        this.reps = reps;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getWeight() {
        return weight;
    }

    public Long getSetNumber() {
        return setNumber;
    }

    public Long getReps() {
        return reps;
    }

    public String getStatus() {
        return status;
    }
}
