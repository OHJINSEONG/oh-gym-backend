package megatera.makaoGymbackEnd.dtos;

public class TrainerResultDto {
    private Long id;
    private String userName;
    private String name;
    private String startTime;
    private String endTime;
    private String status;
    private String phoneNumber;
    private Long age;
    private String gender;
    private String image;

    public TrainerResultDto() {
    }

    public TrainerResultDto(Long id, String userName, String name, String startTime, String endTime, String status, String PhoneNumber, Long age, String gender, String image) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.phoneNumber = PhoneNumber;
        this.age = age;
        this.gender = gender;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }
}
