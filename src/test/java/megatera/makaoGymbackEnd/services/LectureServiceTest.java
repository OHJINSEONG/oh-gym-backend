package megatera.makaoGymbackEnd.services;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.LectureScheduleDto;
import megatera.makaoGymbackEnd.models.Lecture;
import megatera.makaoGymbackEnd.repositories.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
                Lecture.fake("2022-12-09T11:00"),
                Lecture.fake("2022-12-10T13:00"),
                Lecture.fake("2022-12-1T15:00")
        ));

        List<LectureDto> lectureDtos = lectureService.list(trainerId);
    }

    @Test
    void makeDailySchedule() {
        Long trainerId = 1L;

        given(lectureRepository.findAllByTrainerId(trainerId)).willReturn(List.of(
                Lecture.fake("2022-12-09T11:00"),
                Lecture.fake("2022-12-10T13:00"),
                Lecture.fake("2022-12-1T15:00")
        ));

        LectureScheduleDto lectureScheduleDto = lectureService.makeDaliySchedule(trainerId, "09:00", "18:00");

        assertThat(lectureScheduleDto.getTrainerLectureDtos()).hasSize(3);
        assertThat(lectureScheduleDto.getStartTime()).isEqualTo("09:00");
        assertThat(lectureScheduleDto.getEndTime()).isEqualTo("18:00");
    }

    @Test
    void create() {
        Long trainerId = 1L;
        Long cunsumerId = 1L;
        String date = "2022-12-09T11:00";
        String consumerName = "오진욱";

        LectureDto lectureDto = lectureService.create(trainerId, cunsumerId,date,consumerName);

        assertThat(lectureDto.getDate()).isEqualTo(date);
    }
}
