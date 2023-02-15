package megatera.makaoGymbackEnd.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class Period {
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(name = "period")
    private Long value;

    public Period() {
    }

    public Period(Long value) {
        this.value = value;
    }

    public void setStartTime(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(value);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long value() {
        return value;
    }

    @Override
    public String toString() {
        return "Period(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Period.class &&
                Objects.equals(this.value, ((Period) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }
}
