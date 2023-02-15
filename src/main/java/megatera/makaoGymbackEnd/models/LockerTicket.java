package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.LockerTicketDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
public class LockerTicket {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long orderId;

    private Long productId;

    private Status status;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "locker_period"))
    private Period lockerPeriod;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public LockerTicket() {
    }

    public LockerTicket(Long userId, Long orderId, Period lockerPeriod,Long productId) {
        this.userId = userId;
        this.orderId = orderId;
        this.lockerPeriod = lockerPeriod;
        this.productId = productId;
    }

    public static LockerTicket fake(Long userId) {
        Long orderId = 1L;
        Long productId = 1L;

        LockerTicket lockerTicket = new LockerTicket(userId,orderId,new Period(90L),productId);

        lockerTicket.unUsed();

        return lockerTicket;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == LockerTicket.class &&
                Objects.equals(this.id, ((LockerTicket) other).id) &&
                Objects.equals(this.lockerPeriod, ((LockerTicket) other).lockerPeriod);
    }

    public void setStartDate(String startDate) {
        lockerPeriod.setStartTime(LocalDate.parse(startDate));
    }

    public void used() {
        this.status.toUsed();
    }

    public LockerTicketDto toDto() {
        if (status.value().equals("UNUSED")) {
            return new LockerTicketDto(id, lockerPeriod.value(), status.value());
        }
        return new LockerTicketDto(id, lockerPeriod.startDate().toString(), lockerPeriod.endDate().toString(), status.value());
    }

    public void unUsed() {
        this.status = new Status("UNUSED");
    }

    public Status status() {
        return status;
    }

    public Period lockerPeriod() {
        return lockerPeriod;
    }
}
