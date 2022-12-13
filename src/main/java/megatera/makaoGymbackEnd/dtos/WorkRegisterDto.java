package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class WorkRegisterDto {
    private Long trainerId;
    private String date;
    private Integer countOfWeek;
    private String startTime;
    private String endTime;
    private List<Integer> dayOfWeek;

    public WorkRegisterDto(Long trainerId, String date, Integer countOfWeek, String startTime, String endTime, List<Integer> dayOfWeek) {
        this.trainerId = trainerId;
        this.date = date;
        this.countOfWeek = countOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public String getDate() {
        return date;
    }

    public Integer getCountOfWeek() {
        return countOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public List<Integer> getDayOfWeek() {
        return dayOfWeek;
    }
}
