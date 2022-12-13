package megatera.makaoGymbackEnd.dtos;

import java.util.List;
import megatera.makaoGymbackEnd.models.Option;

public class ProductRegisterDto {
    private String title;

    private Long trainerId;

    private List<Option> options;

    public ProductRegisterDto(String title, Long trainerId, List<Option> options) {
        this.title = title;
        this.trainerId = trainerId;
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public List<Option> getOptions() {
        return options;
    }
}
