package megatera.makaoGymbackEnd.dtos;


public class ProductDto {
    private Long id;

    private String title;

    private String trainer;

    private String time;

    private String dayOfWeek;

    private int price;

    private Integer dateOfUse;

    private Integer ptTimes;

    public ProductDto() {
    }

    public ProductDto(Long id,
                      String title,
                      String trainer,
                      Integer dateOfUse,
                      Integer ptTimes,
                      String time,
                      String dayOfWeek,
                      int price) {
        this.id = id;
        this.title = title;
        this.trainer = trainer;
        this.dateOfUse = dateOfUse;
        this.ptTimes = ptTimes;
        this.time = time;
        this.dayOfWeek = dayOfWeek;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTrainer() {
        return trainer;
    }

    public Integer getDateOfUse() {
        return dateOfUse;
    }

    public Integer getPtTimes() {
        return ptTimes;
    }

    public String getTime() {
        return time;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getPrice() {
        return price;
    }
}
