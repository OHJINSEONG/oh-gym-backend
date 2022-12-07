package megatera.makaoGymbackEnd.models;

import java.util.Calendar;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class LectureTest {
    @Test
    void setDate() {
        Long orderId = 1L;
        Lecture lecture = Lecture.fake(orderId);

        assertThat(lecture.date()).isEqualTo(null);

        Calendar calendar = Calendar.getInstance();

        int tuesday = 3;

        lecture.setDate(calendar, tuesday, "2022/12/6");

        assertThat(lecture.date()).isEqualTo("2022/12/6");

        int nextTuesday = 10;

        lecture.setDate(calendar, nextTuesday, "2022/12/6");

        assertThat(lecture.date()).isEqualTo("2022/12/13");
    }

    @Test
    void toDto() {
        Long orderId = 1L;
        Lecture lecture = Lecture.fake(orderId);

        LectureDto lectureDto = lecture.toDto();

        assertThat(lectureDto.getTime()).isEqualTo("11:00");
        assertThat(lectureDto.getCunsumer()).isEqualTo("오진성");
        assertThat(lectureDto.getTrainer()).isEqualTo("오진욱");
    }
}
