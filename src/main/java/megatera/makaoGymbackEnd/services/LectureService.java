package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.TrainerLecturesDto;
import megatera.makaoGymbackEnd.exceptions.RequestFailed;
import megatera.makaoGymbackEnd.models.Lecture;
import megatera.makaoGymbackEnd.models.Name;
import megatera.makaoGymbackEnd.repositories.LectureRepository;
import megatera.makaoGymbackEnd.repositories.PtTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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

    public List<LectureDto> userLectures(Long userId) {
        List<Lecture> userLectures = lectureRepository.findAllByUserId(userId);

        return userLectures.stream().map(Lecture::toDto).toList();
    }

    public LectureDto reserve(Long consumerId, Long trainerId, String dateTime, String consumerName) {
        LocalDateTime requestDateTime = LocalDateTime.parse(dateTime);

        String[] localDateTimes = requestDateTime.toString().split("T");

        if (lectureRepository.findByDateAndTimeAndTrainerId(LocalDate.parse(localDateTimes[0]), LocalTime.parse(localDateTimes[1]), trainerId).isPresent()) {
            throw new RequestFailed(requestDateTime.toString());
        }

        if (lectureRepository.findByDateAndUserId(LocalDate.parse(localDateTimes[0]), consumerId).isPresent()) {
            throw new RequestFailed(requestDateTime.toString());
        }

        Lecture lecture = new Lecture(trainerId, consumerId, LocalDate.parse(localDateTimes[0]),
                LocalTime.parse(localDateTimes[1]), new Name(consumerName));

        lecture.setStatusReserved();

        lectureRepository.save(lecture);

        return lecture.toDto();
    }

    public LectureDto approve(Long lectureId) {
        Lecture lecture = lectureRepository.getReferenceById(lectureId);

        lecture.approve();

        return lecture.toDto();
    }

    public void delete(Long lectureId) {
        Lecture lecture = lectureRepository.getReferenceById(lectureId);

        lectureRepository.delete(lecture);
    }

    public LectureDto find(Long lectureId) {
        return lectureRepository.getReferenceById(lectureId).toDto();
    }
}
