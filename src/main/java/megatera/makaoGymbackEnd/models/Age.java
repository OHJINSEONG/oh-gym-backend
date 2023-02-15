package megatera.makaoGymbackEnd.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Embeddable
public class Age {
    @Column(name = "age")
    private Long value;

    public Age() {
    }

    public Age(String value) {
        Long now = Long.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        Long identifier = Long.valueOf("19" + value.split("-")[0]);

        String age = String.valueOf((now - identifier)).substring(0, 2);

        this.value = Long.parseLong(age);
    }

    public Long value() {
        return value;
    }

    @Override
    public String toString() {
        return "Age(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Age.class &&
                Objects.equals(this.value, ((Age) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
