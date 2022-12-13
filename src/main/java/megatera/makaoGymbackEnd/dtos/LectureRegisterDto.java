package megatera.makaoGymbackEnd.dtos;

public class LectureRegisterDto {
    private Long orderId;
    private String trainer;
    private String consumer;
    private Integer ptTimes;
    private String timeOfPt;
    private String dayOfWeek;
    private String ptStartDate;

    public LectureRegisterDto(Long orderId,
                              String trainer,
                              String consumer,
                              Integer ptTimes,
                              String timeOfPt,
                              String dayOfWeek,
                              String ptStartDate) {
        this.orderId = orderId;
        this.trainer = trainer;
        this.consumer = consumer;
        this.ptTimes = ptTimes;
        this.timeOfPt = timeOfPt;
        this.dayOfWeek = dayOfWeek;
        this.ptStartDate = ptStartDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getConsumer() {
        return consumer;
    }

    public Integer getPtTimes() {
        return ptTimes;
    }

    public String getTimeOfPt() {
        return timeOfPt;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getPtStartDate() {
        return ptStartDate;
    }
}
