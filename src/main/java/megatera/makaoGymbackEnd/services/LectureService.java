package megatera.makaoGymbackEnd.services;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.TrainerLecturesDto;
import megatera.makaoGymbackEnd.models.Lecture;
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

    public List<LectureDto> list(Long trainerId) {
        return lectureRepository.findAllByTrainerId(trainerId).stream().map(Lecture::toDto).toList();
    }

    public TrainerLecturesDto trainerLectures(Long trainerId, String startTime, String endTime) {
        List<Lecture> trainerLectures = lectureRepository.findAllByTrainerId(trainerId);

        List<LectureDto> lectureDtos = trainerLectures.stream().map(Lecture::toDto).toList();

        return new TrainerLecturesDto(lectureDtos, startTime, endTime);
    }

    public LectureDto create(Long trainerId, Long consumerId, String localDateTime, String consumerName) {
        String[] localDateTimes = localDateTime.split("T");

        Lecture lecture = new Lecture(trainerId, consumerId, localDateTimes[0], localDateTimes[1], consumerName);

        lecture.setStatusCreated();

        lectureRepository.save(lecture);

        return lecture.toDto();
    }

    public List<LectureDto> userLectures(Long userId) {
        List<Lecture> userLectures = lectureRepository.findAllByUserId(userId);

        return userLectures.stream().map(Lecture::toDto).toList();
    }
}
