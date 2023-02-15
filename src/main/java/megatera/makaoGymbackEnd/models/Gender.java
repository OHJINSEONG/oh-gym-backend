package megatera.makaoGymbackEnd.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Gender {
    @Column(name = "gender")
    private String value;

    public Gender() {
    }

    public Gender(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "Gender(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Gender.class &&
                Objects.equals(this.value, ((Gender) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
