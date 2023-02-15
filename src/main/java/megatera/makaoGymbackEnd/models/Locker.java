package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.LockerDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Locker {
    @Id
    @GeneratedValue
    private Long id;

    private Long lockerNumber;

    private Long userId;

    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Locker() {
    }

    public Locker(Long lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    public static Locker fake(Long lockerNumber) {
        Locker locker = new Locker(lockerNumber);

        locker.inAvailable();

        return locker;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Status status() {
        return status;
    }

    public LockerDto toDto() {
        return new LockerDto(id, lockerNumber, status.value());
    }

    public void inUse() {
        this.status.toUsed();
    }

    public void reserved() {
        this.status.toReserved();
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void inAvailable() {
        this.status = new Status("AVAILABLE");
    }

    public void deleteUserId() {
        this.userId = null;
    }
}
