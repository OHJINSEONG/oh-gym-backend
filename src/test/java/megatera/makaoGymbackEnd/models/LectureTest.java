package megatera.makaoGymbackEnd.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class LectureTest {
    @Test
    void setStatusReserved() {
        String date = "2022-12-08";

        Lecture lecture = Lecture.fake(date);

        lecture.setStatusReserved();

        assertThat(lecture.status().value()).isEqualTo("RESERVED");
    }

    @Test
    void approve() {
        String date = "2022-12-08";

        Lecture lecture = Lecture.fake(date);

        lecture.approve();

        assertThat(lecture.status().value()).isEqualTo("APPROVE");
    }
}
