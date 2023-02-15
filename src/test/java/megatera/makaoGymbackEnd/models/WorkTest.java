package megatera.makaoGymbackEnd.models;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class WorkTest {
    @Test
    void setStatusCreated() {
        Work work = Work.fake(1L);

        work.setStatusCreated();

        assertThat(work.status().value()).isEqualTo("CREATED");
    }

    @Test
    void setDate() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek1 = 3;
        int dayOfWeek2 = 10;
        int dayOfWeek3 = 4;
        int startYear = 2022;
        int startMonth = 12;
        int startDate = 20;

        Work work = Work.fake(1L);

        work.setDate(calendar, dayOfWeek1, startYear, startMonth, startDate);

        assertThat(work.date().toString()).isEqualTo("2022-12-20");

        work.setDate(calendar, dayOfWeek2, startYear, startMonth, startDate);

        assertThat(work.date().toString()).isEqualTo("2022-12-27");

        work.setDate(calendar, dayOfWeek3, startYear, startMonth, startDate);

        assertThat(work.date().toString()).isEqualTo("2022-12-21");
    }
}
