package megatera.makaoGymbackEnd.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Count {
    @Column(name = "count")
    private Long value;

    public Count() {
    }

    public Count(Long value) {
        this.value = value;
    }

    public Long value() {
        return value;
    }

    @Override
    public String toString() {
        return "Count(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Count.class &&
                Objects.equals(this.value, ((Count) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public void check() {
        this.value -= 1L;
    }

    public void cancel() {
        this.value += 1L;
    }
}
