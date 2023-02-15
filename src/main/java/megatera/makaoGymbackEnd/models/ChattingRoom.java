package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.ChattingRoomDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ChattingRoom {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long trainerId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_name"))
    private UserName userName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "trainer_name"))
    private UserName trainerName;

    private String trainerImage;

    private String userEmail;

    private String message;

    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ChattingRoom() {
    }

    public ChattingRoom(Long userId, Long trainerId, UserName userName, UserName trainerName, String trainerImage, String userEmail) {
        this.userId = userId;
        this.trainerId = trainerId;
        this.userName = userName;
        this.trainerName = trainerName;
        this.trainerImage = trainerImage;
        this.userEmail = userEmail;
    }

    public static ChattingRoom fake(UserName userName) {
        Long userId = 1L;
        Long trainerId = 1L;
        UserName trainerName = new UserName("오진욱");
        String trainerImage = "image";
        String userEmail = "email";

        ChattingRoom chattingRoom = new ChattingRoom(userId,trainerId,userName,trainerName,trainerImage,userEmail);

        chattingRoom.updateTime(LocalDateTime.now());

        chattingRoom.created();

        return chattingRoom;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == ChattingRoom.class &&
                Objects.equals(this.id, ((ChattingRoom) other).id);
    }

    public void created() {
        status = new Status("CREATED");
    }

    public ChattingRoomDto toDto() {
        return new ChattingRoomDto(id, status.value(), userName.value(), userEmail, trainerName.value(), updatedAt, message, trainerImage);
    }

    public Long trainerId() {
        return trainerId;
    }

    public Long userId() {
        return userId;
    }

    public void updateTime(LocalDateTime updateTime) {
        this.updatedAt = updateTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long id() {
        return id;
    }

    public LocalDateTime updateAt() {
        return updatedAt;
    }

    public String message() {
        return message;
    }
}
