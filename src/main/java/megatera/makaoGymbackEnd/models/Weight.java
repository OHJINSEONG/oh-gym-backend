package megatera.makaoGymbackEnd.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Weight {
    @Column(name = "weight")
    private Long value;

    public Weight() {
    }

    public Weight(Long value) {
        this.value = value;
    }

    public Long value() {
        return value;
    }

    @Override
    public String toString() {
        return "Weight(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Weight.class &&
                Objects.equals(this.value, ((Weight) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
