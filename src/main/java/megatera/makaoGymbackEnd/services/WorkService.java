package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.WorkDto;
import megatera.makaoGymbackEnd.models.Work;
import megatera.makaoGymbackEnd.repositories.WorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class WorkService {
    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public  List<WorkDto> findWithDate(String date) {
        return workRepository.findAllByDate(date);
    }

    public List<WorkDto> createWork(
            Long trainerId,
            String date,
            Integer countOfWeek,
            String startTime,
            String endTime,
            List<Integer> dayOfWeek) {
        List<WorkDto> workDtos = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int index = 0;
        int weekIndex = 0;
        int startYear = Integer.parseInt(date.split("-")[0]);
        int startMonth = Integer.parseInt(date.split("-")[1]);
        int startDate = Integer.parseInt(date.split("-")[2]);

        for (int i = 0; i < countOfWeek * dayOfWeek.size(); i += 1) {
            if (index == dayOfWeek.size()) {
                index = 0;
                weekIndex += 1;
            }
            int dayOfweek = dayOfWeek.get(index) + weekIndex * 7;

            index += 1;

            if (dayOfweek < calendar.get(Calendar.DAY_OF_WEEK)) {
                continue;
            }

            Work work = new Work(trainerId, startTime, endTime);
            work.setStatusCreated();
            work.setDate(calendar, dayOfweek, startYear, startMonth, startDate);
            workRepository.save(work);

            workDtos.add(work.toDto());
        }

        return workDtos;
    }

    public List<WorkDto> list(Long trainerId) {
        return workRepository.findAllByTrainerId(trainerId).stream().map(Work::toDto).toList();
    }
}
