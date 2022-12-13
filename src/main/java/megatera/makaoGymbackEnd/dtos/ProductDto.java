package megatera.makaoGymbackEnd.dtos;


public class ProductDto {
    private Long id;

    private String title;

    private Long trainerId;

    public ProductDto(Long id, String title, Long trainerId) {
        this.id = id;
        this.title = title;
        this.trainerId = trainerId;
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
}
