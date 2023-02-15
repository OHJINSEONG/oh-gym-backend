package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class ScheduleDetailDto {
    private List<String> trainerSchedules;
    private String date;

    public ScheduleDetailDto(List<String> trainerSchedules, String date) {
        this.trainerSchedules = trainerSchedules;
        this.date = date;
    }

    public List<String> getTrainerSchedules() {
        return trainerSchedules;
    }

    public String getDate() {
        return date;
    }
}
