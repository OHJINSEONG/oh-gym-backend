package megatera.makaoGymbackEnd.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.models.Lecture;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.repositories.LectureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }


    public List<LectureDto> makeList(Long orderId,
                                     String trainer,
                                     String consumer,
                                     Integer ptTimes,
                                     String timeOfPt,
                                     String dayOfWeeks,
                                     String ptStartDate) {
        Calendar calendar = Calendar.getInstance();

        List<Lecture> lectures = new ArrayList<>();

        int index = 0;
        int weekIndex = 0;

        String dayToNumber = dayOfWeeks.equals("월 수 일") ? "2 4 6" : "3 5";
        int[] ptDayOfWeek = Arrays.stream(dayToNumber.split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < ptTimes; i += 1) {
            if (index == ptDayOfWeek.length) {
                index = 0;
                weekIndex += 1;
            }
            int dayOfWeek = ptDayOfWeek[index];

            index += 1;

            Lecture lecture = new Lecture(orderId, new UserName(trainer), new UserName(consumer), timeOfPt);
            lecture.setDate(calendar, dayOfWeek + weekIndex * 7, ptStartDate);
            lectureRepository.save(lecture);
            lectures.add(lecture);
        }

        return lectures.stream().map(Lecture::toDto).toList();
    }

    public List<LectureDto> list() {
        return lectureRepository.findAll().stream().map(Lecture::toDto).toList();
    }
}
