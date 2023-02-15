package megatera.makaoGymbackEnd.dtos;

public class DiaryPatchDto {
    private String memo;
    private String time;

    public DiaryPatchDto() {
    }

    public DiaryPatchDto(String memo, String time) {
        this.memo = memo;
        this.time = time;
    }

    public String getMemo() {
        return memo;
    }

    public String getTime() {
        return time;
    }
}
