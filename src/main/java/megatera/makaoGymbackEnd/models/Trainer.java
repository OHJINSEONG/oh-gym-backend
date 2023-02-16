package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Trainer {
    @Id
    @GeneratedValue
    private Long id;

    private UserName userName;

    private Name name;

    private PhoneNumber phoneNumber;

    private Age age;

    private Gender gender;

    private String image;

    private LocalTime startTime;

    private LocalTime endTime;

    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Trainer() {
    }

    public Trainer(UserName userName, Name name, PhoneNumber phoneNumber, Age age, Gender gender, String image, LocalTime startTime, LocalTime endTime) {
        this.userName = userName;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.image = image;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Trainer(Long id, UserName userName, Name name, PhoneNumber phoneNumber, Age age, Gender gender, String image, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.image = image;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Trainer fake(UserName userName) {
        Trainer trainer = new Trainer(1L, userName, new Name("오진성"), new PhoneNumber("01085568955"), new Age("971117-1932123"), new Gender("남자"), "Qweqwe", LocalTime.parse("09:00"), LocalTime.parse("18:00"));

        trainer.toCreated();

        return trainer;
    }

    public TrainerResultDto toDto() {
        return new TrainerResultDto(
                id,
                userName.value(),
                name.value(),
                startTime.toString(),
                endTime.toString(),
                status.value(),
                phoneNumber.value(),
                age.value(),
                gender.value(),
                image
        );
    }

    public UserName userName() {
        return userName;
    }

    public Name name() {
        return name;
    }

    public LocalTime startTime() {
        return startTime;
    }

    public LocalTime endTime() {
        return endTime;
    }

    public Status status() {
        return status;
    }

    public Age age() {
        return age;
    }

    public String image() {
        return image;
    }

    public Long id() {
        return id;
    }

    public void toCreated() {
        this.status = new Status("CREATED");
    }
}
