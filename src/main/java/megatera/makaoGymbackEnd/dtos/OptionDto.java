package megatera.makaoGymbackEnd.dtos;

public class OptionDto {
    private Long id;
    private Integer ptTimes;
    private Integer dateOfUse;
    private Integer price;

    public OptionDto(Long id, Integer ptTimes, Integer dateOfUse, Integer price) {
        this.id = id;
        this.ptTimes = ptTimes;
        this.dateOfUse = dateOfUse;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Integer getPtTimes() {
        return ptTimes;
    }

    public Integer getDateOfUse() {
        return dateOfUse;
    }

    public Integer getPrice() {
        return price;
    }
}
