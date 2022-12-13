package megatera.makaoGymbackEnd.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import megatera.makaoGymbackEnd.dtos.UserDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "PERSON")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private UserName userName;

    private String name;

    private Integer ptTimes;

    private Integer periodOfUse;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(UserName userName, String name, Integer ptTimes, Integer periodOfUse) {
        this.userName = userName;
        this.name = name;
        this.ptTimes = ptTimes;
        this.periodOfUse = periodOfUse;
    }

    public static User fake(UserName username) {
        return new User(username, "오진성", 12, 90);
    }

    public UserDto toDto() {
        return new UserDto(id, userName, name, ptTimes, periodOfUse);
    }
}
