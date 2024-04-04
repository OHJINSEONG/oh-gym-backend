package megatera.makaoGymbackEnd.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SetResultDto {
    private Long id;
    private Long weight;
    private Long setNumber;
    private Long reps;
    private String status;

    @JsonCreator
    public SetResultDto(
            @JsonProperty("id") Long id,
            @JsonProperty("weight") Long weight,
            @JsonProperty("setNumber") Long setNumber,
            @JsonProperty("reps") Long reps,
            @JsonProperty("status") String status
    ) {
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
