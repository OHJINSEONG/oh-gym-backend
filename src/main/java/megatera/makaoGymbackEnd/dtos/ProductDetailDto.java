package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class ProductDetailDto {
    private Long id;

    private String title;

    private Long trainerId;

    private String type;

    private List<OptionResultDto> options;

    private String trainerUserName;

    private String trainerImage;

    public ProductDetailDto(Long id, String title, Long trainerId, String type, List<OptionResultDto> options, String trainerUserName, String trainerImage) {
        this.id = id;
        this.title = title;
        this.trainerId = trainerId;
        this.type = type;
        this.options = options;
        this.trainerUserName = trainerUserName;
        this.trainerImage = trainerImage;
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

    public List<OptionResultDto> getOptions() {
        return options;
    }

    public String getTrainerUserName() {
        return trainerUserName;
    }

    public String getTrainerImage() {
        return trainerImage;
    }
}
