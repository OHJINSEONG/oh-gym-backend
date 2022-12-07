package megatera.makaoGymbackEnd.dtos;

public class LectureDto {
    private Long id;
    private String trainer;
    private String cunsumer;
    private String date;
    private String time;

    public LectureDto(Long id, String trainer, String cunsumer, String date, String time) {
        this.id = id;
        this.trainer = trainer;
        this.cunsumer = cunsumer;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getCunsumer() {
        return cunsumer;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
