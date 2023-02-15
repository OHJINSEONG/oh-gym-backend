package megatera.makaoGymbackEnd.dtos;

public class ChattingEnterDto {
    private String userName;
    private String trainerName;
    private String trainerImage;

    public ChattingEnterDto() {
    }

    public ChattingEnterDto(String userName, String trainerName, String trainerImage) {
        this.userName = userName;
        this.trainerName = trainerName;
        this.trainerImage = trainerImage;
    }

    public String getUserName() {
        return userName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public String getTrainerImage() {
        return trainerImage;
    }
}
