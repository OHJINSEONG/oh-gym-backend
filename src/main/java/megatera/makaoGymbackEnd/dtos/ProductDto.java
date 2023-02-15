package megatera.makaoGymbackEnd.dtos;


public class ProductDto {
    private Long id;

    private String title;

    private Long trainerId;

    private String type;

    public ProductDto(Long id, String title, String type, Long trainerId) {
        this.id = id;
        this.title = title;
        this.trainerId = trainerId;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public String getType() {
        return type;
    }
}
