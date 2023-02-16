package megatera.makaoGymbackEnd.dtos;

public class TrainerRegisterDto {
    private String name;
    private String userName;
    private String image;

    public TrainerRegisterDto() {
    }

    public TrainerRegisterDto(String name, String userName, String image) {
        this.name = name;
        this.userName = userName;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getImage() {
        return image;
    }
}
