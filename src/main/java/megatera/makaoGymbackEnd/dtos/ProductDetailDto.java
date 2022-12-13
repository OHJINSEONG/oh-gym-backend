package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class ProductDetailDto {
    private Long id;

    private String title;

    private Long trainerId;

    private List<OptionDto> options;

    public ProductDetailDto(Long id, String title, Long trainerId, List<OptionDto> options) {
        this.id = id;
        this.title = title;
        this.trainerId = trainerId;
        this.options = options;
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

    public List<OptionDto> getOptions() {
        return options;
    }
}
