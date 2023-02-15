package megatera.makaoGymbackEnd.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name {
    @Column(name = "name")
    private String value;

    public Name() {
    }

    public Name(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "Name(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Name.class &&
                Objects.equals(this.value, ((Name) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
