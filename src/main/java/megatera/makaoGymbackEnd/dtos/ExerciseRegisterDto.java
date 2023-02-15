package megatera.makaoGymbackEnd.dtos;

public class ExerciseRegisterDto {
    private String name;
    private String type;
    private Long diaryId;

    public ExerciseRegisterDto(String name, String type, Long diaryId) {
        this.name = name;
        this.type = type;
        this.diaryId = diaryId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getDiaryId() {
        return diaryId;
    }
}
