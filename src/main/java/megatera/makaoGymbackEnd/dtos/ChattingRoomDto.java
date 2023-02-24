package megatera.makaoGymbackEnd.dtos;

import java.time.LocalDateTime;

public class ChattingRoomDto {
    private Long id;
    private String status;
    private String userName;
    private String userEmail;
    private String trainerName;
    private LocalDateTime updateTime;
    private String trainerImage;
    private String message;
    private Long trainerId;

    public ChattingRoomDto() {
    }

    public ChattingRoomDto(
            Long id,
            String status,
            String userName,
            String userEmail,
            String trainerName,
            LocalDateTime updateTime,
            String message,
            String trainerImage,
            Long trainerId
    ) {
        this.id = id;
        this.status = status;
        this.userName = userName;
        this.userEmail = userEmail;
        this.trainerName = trainerName;
        this.updateTime = updateTime;
        this.trainerImage = trainerImage;
        this.message = message;
        this.trainerId = trainerId;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getTrainerImage() {
        return trainerImage;
    }

    public Long getTrainerId() {
        return trainerId;
    }
}
