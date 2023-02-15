package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.TrainerLecturesDto;
import megatera.makaoGymbackEnd.models.Lecture;
import megatera.makaoGymbackEnd.repositories.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LectureServiceTest {
    private LectureRepository lectureRepository;

    private LectureService lectureService;

    @BeforeEach
    void setup() {
        lectureRepository = mock(LectureRepository.class);
        lectureService = new LectureService(lectureRepository);
    }

    @Test
    void list() {
        Long trainerId = 1L;

        given(lectureRepository.findAllByTrainerId(trainerId)).willReturn(List.of(
                Lecture.fake("2022-12-09"),
                Lecture.fake("2022-12-10"),
                Lecture.fake("2022-12-01")
        ));

        List<LectureDto> lectureDtos = lectureService.list(trainerId);

        assertThat(lectureDtos).hasSize(3);
    }

    @Test
    void trainerLectures() {
        Long trainerId = 1L;

        given(lectureRepository.findAllByTrainerId(trainerId)).willReturn(List.of(
                Lecture.fake("2022-12-09"),
                Lecture.fake("2022-12-10"),
                Lecture.fake("2022-12-01")
        ));

        TrainerLecturesDto trainerLecturesDto = lectureService.trainerLectures(trainerId, "09:00", "18:00");

        assertThat(trainerLecturesDto.getLectures()).hasSize(3);
        assertThat(trainerLecturesDto.getStartTime()).isEqualTo("09:00");
        assertThat(trainerLecturesDto.getEndTime()).isEqualTo("18:00");
    }

    @Test
    void userLectures() {
        Long userId = 1L;

        given(lectureRepository.findAllByUserId(userId)).willReturn(List.of(
                Lecture.fake("2022-12-09"),
                Lecture.fake("2022-12-10"),
                Lecture.fake("2022-12-01")
        ));

        List<LectureDto> lectureDtos = lectureService.userLectures(userId);

        assertThat(lectureDtos).hasSize(3);
    }

    @Test
    void reserve() {
        Long lectureId = 1L;
        Long consumerId = 2L;
        Long trainerId = 3L;
        String dateTime = "2022-12-09T11:00";
        String consumerName = "오진성";

        given(lectureRepository.getReferenceById(lectureId)).willReturn(
                Lecture.fake(dateTime.split("T")[0])
        );

        LectureDto lectureDto = lectureService.reserve(consumerId, trainerId, dateTime, consumerName);

        assertThat(lectureDto.getDate()).isEqualTo(dateTime.split("T")[0]);
        assertThat(lectureDto.getStatus()).isEqualTo("RESERVED");
    }

    @Test
    void approve() {
        Long lectureId = 1L;

        given(lectureRepository.getReferenceById(lectureId)).willReturn(
                Lecture.fake("2022-12-09")
        );

        LectureDto lectureDto = lectureService.approve(lectureId);

        assertThat(lectureDto.getStatus()).isEqualTo("APPROVE");
    }

    @Test
    void find() {
        Long lectureId = 1L;

        given(lectureRepository.getReferenceById(lectureId)).willReturn(
                Lecture.fake("2022-12-09")
        );

        LectureDto lectureDto = lectureService.find(lectureId);

        assertThat(lectureDto.getDate()).isEqualTo("2022-12-09");
    }
}
