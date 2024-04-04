package megatera.makaoGymbackEnd.dtos;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExerciseResultDto {
    private List<SetResultDto> sets;
    private ExerciseDto exercise;

    @JsonCreator
    public ExerciseResultDto(@JsonProperty("exercise") ExerciseDto exercise, @JsonProperty("sets") List<SetResultDto> sets) {
        this.exercise = exercise;
        this.sets = sets;
    }

    public ExerciseDto getExercise() {
        return exercise;
    }

    public List<SetResultDto> getSets() {
        return sets;
    }
}
