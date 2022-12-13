package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class LectureScheduleDto {
    private List<LectureDto> trainerLectureDtos;

    private String startTime;

    private String endTime;

    public LectureScheduleDto(List<LectureDto> trainerLectureDtos, String startTime, String endTime) {
        this.trainerLectureDtos = trainerLectureDtos;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public List<LectureDto> getTrainerLectureDtos() {
        return trainerLectureDtos;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
//구글캘린더랑 연동 개인스캐줄안에 입력한 서비스랑 연동이되서 겹치는 시간 제거!