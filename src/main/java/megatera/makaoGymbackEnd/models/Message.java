package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.MembershipTicketDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class Message {
    @Column(name = "name")
    private String value;

    public Message() {
    }

    public Message(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "Message(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Message.class &&
                Objects.equals(this.value, ((Message) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
