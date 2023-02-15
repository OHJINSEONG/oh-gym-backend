package megatera.makaoGymbackEnd.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {
    @Test
    void setStartTime(){
        Period period = new Period(90L);

        LocalDate startDate = LocalDate.parse("2022-12-10");

        period.setStartTime(startDate);

        assertThat(period.value()).isEqualTo(90L);
        assertThat(period.startDate()).isEqualTo(startDate);

        LocalDate endDate = startDate.plusDays(period.value());

        assertThat(period.endDate()).isEqualTo(endDate);
    }
}
