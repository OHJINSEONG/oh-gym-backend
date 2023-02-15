package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.UserDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "PERSON")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private UserName userName;

    private String email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "pt_times"))
    private Count ptTimes;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "period_of_use"))
    private Period periodOfUse;

    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(UserName userName, String email, Count ptTimes, Period periodOfUse) {
        this.userName = userName;
        this.email = email;
        this.ptTimes = ptTimes;
        this.periodOfUse = periodOfUse;
    }

    public static User fake(UserName username) {
        User user = new User(username, "email", new Count(12L), new Period(90L));

        user.created();

        return user;
    }

    public UserDto toDto() {
        return new UserDto(id, userName.value(), email, ptTimes.value(), periodOfUse.value(), status.value());
    }

    public UserName userName() {
        return userName;
    }

    public Count ptTimes() {
        return ptTimes;
    }

    public Period periodOfUse() {
        return periodOfUse;
    }

    public String email() {
        return email;
    }

    public void created() {
        this.status = new Status("CREATED");
    }
}
