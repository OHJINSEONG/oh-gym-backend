package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class ProductRegisterDto {
    private String title;

    private Long trainerId;

    private String type;

    private List<OptionDto> options;

    public ProductRegisterDto() {
    }

    public ProductRegisterDto(String title, Long trainerId, String type, List<OptionDto> options) {
        this.title = title;
        this.trainerId = trainerId;
        this.type = type;
        this.options = options;
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

    public List<OptionDto> getOptions() {
        return options;
    }
}
