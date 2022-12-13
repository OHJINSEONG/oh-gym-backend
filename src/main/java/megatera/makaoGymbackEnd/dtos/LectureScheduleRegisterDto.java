package megatera.makaoGymbackEnd.dtos;

public class LectureScheduleRegisterDto {
    private String date;

    public LectureScheduleRegisterDto(String date){
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
