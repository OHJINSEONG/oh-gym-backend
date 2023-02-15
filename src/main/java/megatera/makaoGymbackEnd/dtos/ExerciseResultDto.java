package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class ExerciseResultDto {
    private List<SetResultDto> sets;
    private ExerciseDto exercise;

    public ExerciseResultDto(ExerciseDto exercise, List<SetResultDto> sets) {
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
