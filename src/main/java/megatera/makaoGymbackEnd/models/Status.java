package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.OptionDto;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Embeddable
public class Status {
    private String value;

    public Status() {
    }

    public Status(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "Status(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Status.class &&
                Objects.equals(this.value, ((Status) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
