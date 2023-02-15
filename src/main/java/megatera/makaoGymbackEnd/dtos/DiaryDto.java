package megatera.makaoGymbackEnd.dtos;

public class DiaryDto {
    private Long id;

    private String date;

    private String status;

    private String memo;

    private String time;

    public DiaryDto(Long id, String date, String status, String memo, String time) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.memo = memo;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getMemo() {
        return memo;
    }

    public String getTime() {
        return time;
    }
}
