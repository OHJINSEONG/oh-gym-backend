package megatera.makaoGymbackEnd.services;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.repositories.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
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
    void makeList() {
        Long orderId = 1L;
        String trainer = "오진욱";
        String consumer = "오진성";
        Integer ptTimes = 12;
        String timeOfPt = "11:00";
        String dayOfWeeks = "월 수 금";
        String ptStartDate = "2022/12/06";

        List<LectureDto> lectureDtos = lectureService.makeList(orderId,
                trainer,
                consumer,
                ptTimes,
                timeOfPt,
                dayOfWeeks,
                ptStartDate
        );

        assertThat(lectureDtos).hasSize(12);
    }
}
