package megatera.makaoGymbackEnd.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LockerTest {
    @Test
    void inUse(){
        Locker locker = Locker.fake(1L);

        locker.inUse();

        assertThat(locker.status().value()).isEqualTo("INUSE");
    }

    @Test
    void reserved(){
        Locker locker = Locker.fake(1L);

        locker.reserved();

        assertThat(locker.status().value()).isEqualTo("RESERVED");
    }

    @Test
    void setUserId(){
        Locker locker = Locker.fake(1L);

        locker.setUserId(2L);

        assertThat(locker.getUserId()).isEqualTo(2L);
    }

    @Test
    void deleteUserId(){
        Locker locker = Locker.fake(1L);

        locker.setUserId(2L);

        locker.deleteUserId();

        assertThat(locker.getUserId()).isEqualTo(null);
    }
}