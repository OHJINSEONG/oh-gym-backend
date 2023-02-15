package megatera.makaoGymbackEnd.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LockerTicketTest {
    @Test
    void used(){
        LockerTicket lockerTicket = LockerTicket.fake(1L);

        lockerTicket.used();

        assertThat(lockerTicket.status().value()).isEqualTo("INUSE");
    }

    @Test
    void toDto(){
        LockerTicket lockerTicket = LockerTicket.fake(1L);

        assertThat(lockerTicket.status().value()).isEqualTo("UNUSED");
        assertThat(lockerTicket.lockerPeriod().value()).isEqualTo(90L);

        lockerTicket.setStartDate("2022-12-01");

        lockerTicket.used();

        assertThat(lockerTicket.status().value()).isEqualTo("INUSE");
        assertThat(lockerTicket.lockerPeriod().startDate().toString()).isEqualTo("2022-12-01");

        LocalDate endDate = lockerTicket.lockerPeriod().startDate().plusDays(lockerTicket.lockerPeriod().value());

        assertThat(lockerTicket.lockerPeriod().endDate()).isEqualTo(endDate);
    }

}