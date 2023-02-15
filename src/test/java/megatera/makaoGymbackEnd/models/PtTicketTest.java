package megatera.makaoGymbackEnd.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PtTicketTest {
    @Test
    void used() {
        PtTicket ptTicket = PtTicket.fake(1L);

        ptTicket.used();

        assertThat(ptTicket.status().value()).isEqualTo("INUSE");

    }

    @Test
    void countPt() {
        PtTicket ptTicket = PtTicket.fake(1L);

        assertThat(ptTicket.ptTimes().value()).isEqualTo(12L);

        ptTicket.countPt();

        assertThat(ptTicket.ptTimes().value()).isEqualTo(11L);

    }

    @Test
    void cancelPt() {
        PtTicket ptTicket = PtTicket.fake(1L);

        assertThat(ptTicket.ptTimes().value()).isEqualTo(12L);

        ptTicket.cancelPt();

        assertThat(ptTicket.ptTimes().value()).isEqualTo(13L);
    }
}
