package megatera.makaoGymbackEnd.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Status {
    @Column(name = "status")
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

    public void toChecked() {
        this.value = "CHECKED";
    }

    public void toDeleted() {
        this.value = "DELETED";
    }

    public void toUsed() {
        this.value = "INUSE";
    }

    public void toReserved() {
        this.value = "RESERVED";
    }

    public void toAvailable() {
        this.value = "AVAILABLE";
    }

    public void toComplete() {
        this.value = "COMPLETE";
    }

    public void toApprove() {
        this.value = "APPROVE";
    }
}
