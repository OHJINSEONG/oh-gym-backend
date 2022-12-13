package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class TrainerLecturesDto {
    private List<LectureDto> lectures;
    private String startTime;
    private String endTime;

    public TrainerLecturesDto(List<LectureDto> lectures, String startTime, String endTime) {
        this.lectures = lectures;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public List<LectureDto> getLectures() {
        return lectures;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
