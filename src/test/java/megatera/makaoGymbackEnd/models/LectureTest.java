package megatera.makaoGymbackEnd.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class LectureTest {
//    @Test
//    void setDate() {
//        Long orderId = 1L;
//        Lecture lecture = Lecture.fake(orderId);
//
//        assertThat(lecture.date()).isEqualTo(null);
//
//        Calendar calendar = Calendar.getInstance();
//
//        int tuesday = 3;
//
//        lecture.setDate(calendar, tuesday, "2022-12-06");
//
//        assertThat(lecture.date()).isEqualTo("2022-12-06");
//
//        int monday = 2;
//
//        lecture.setDate(calendar, monday, "2022-12-06");
//
//        assertThat(lecture.date()).isEqualTo("2022-12-12");
//
//        int nextTuesday = 10;
//
//        lecture.setDate(calendar, nextTuesday, "2022-12-6");
//
//        assertThat(lecture.date()).isEqualTo("2022-12-13");
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
//    }

    @Test
    void toDto() {
        String date = "2022-12-08T11:00";
        Lecture lecture = Lecture.fake(date);

        lecture.setStatusCreated();

        LectureDto lectureDto = lecture.toDto();

        assertThat(lectureDto.getDate()).isEqualTo("2022-12-08T11:00");
        assertThat(lectureDto.getStatus()).isEqualTo("CREATED");
    }
}
