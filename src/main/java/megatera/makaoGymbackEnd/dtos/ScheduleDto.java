package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class ScheduleDto {
    private List<String> emptySchedules;

    public ScheduleDto(List<String> emptySchedules) {
        this.emptySchedules = emptySchedules;
    }

    public List<String> getEmptySchedules() {
        return emptySchedules;
    }
}
