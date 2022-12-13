package megatera.makaoGymbackEnd.models;

import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class TaskTime {
    private String value;

    public TaskTime() {
    }

    public TaskTime(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == TaskTime.class &&
                Objects.equals(this.value, ((TaskTime) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
