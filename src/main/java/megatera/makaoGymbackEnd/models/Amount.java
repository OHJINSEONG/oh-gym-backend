package megatera.makaoGymbackEnd.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Amount {
    @Column(name = "amount")
    private Long value;

    public Amount() {
    }

    public Amount(Long value) {
        this.value = value;
    }

    public Long value() {
        return value;
    }

    @Override
    public String toString() {
        return "Amount(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Amount.class &&
                Objects.equals(this.value, ((Amount) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
