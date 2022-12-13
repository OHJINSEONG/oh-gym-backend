package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.ScheduleDto;
import megatera.makaoGymbackEnd.dtos.WorkDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    public ScheduleDto createSchedule(List<LectureDto> lectureDtos, List<WorkDto> workDtos) {
        List<String> trainerSchedules = new ArrayList<>();

        for (WorkDto workDto : workDtos) {
            List<String> trainerLectures = lectureDtos.stream()
                    .filter(lectureDto -> lectureDto.getDate().equals(workDto.getDate()))
                    .map(LectureDto::getTime)
                    .toList();

            int startTime = Integer.parseInt(workDto.getStartTime().split(":")[0]);
            int endTime = Integer.parseInt(workDto.getEndTime().split(":")[0]);

            for (int i = startTime; i < endTime; i += 1) {
                String time = String.valueOf(i) + ":00";

                trainerSchedules.add(time);
            }
            trainerSchedules.removeAll(trainerLectures);
        }
        return new ScheduleDto(trainerSchedules);
    }
}
