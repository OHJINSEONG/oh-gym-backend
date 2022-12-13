package megatera.makaoGymbackEnd.dtos;

public class LectureDto {
    private Long id;
    private String userName;
    private String status;
    private String date;
    private String time;

    public LectureDto(Long id, String userName, String status, String date, String time) {
        this.id = id;
        this.userName = userName;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
