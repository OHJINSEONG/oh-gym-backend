package megatera.makaoGymbackEnd.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Option {
    @Column(name = "option")
    private Integer value;

    public Option() {
    }

    public Option(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return "Option(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == UserName.class &&
                Objects.equals(this.value, ((Option) other).value);
    }
}
