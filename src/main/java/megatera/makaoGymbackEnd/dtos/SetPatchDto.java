package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class SetPatchDto {
    private Long exerciseId;
    private List<SetDto> sets;

    public SetPatchDto() {
    }

    public SetPatchDto(Long exerciseId, List<SetDto> sets) {
        this.exerciseId = exerciseId;
        this.sets = sets;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public List<SetDto> getSets() {
        return sets;
    }
}
