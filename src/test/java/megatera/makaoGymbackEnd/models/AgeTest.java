package megatera.makaoGymbackEnd.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AgeTest {
    @Test
    void value() {
        Age age1 = new Age("971117-1922222");
        Age age2 = new Age("970117-1922222");

        assertThat(age1.value()).isEqualTo(25L);
        assertThat(age2.value()).isEqualTo(26L);
    }
}
