package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class DiaryResultDto {
    private DiaryDto diary;
    private List<ExerciseResultDto> exerciseInformations;

    public DiaryResultDto() {
    }

    public DiaryResultDto(DiaryDto diary, List<ExerciseResultDto> exerciseInformations) {
        this.diary = diary;
        this.exerciseInformations = exerciseInformations;
    }

    public DiaryDto getDiary() {
        return diary;
    }

    public List<ExerciseResultDto> getExerciseInformations() {
        return exerciseInformations;
    }
}
