package megatera.makaoGymbackEnd.dtos;

public class OptionResultDto {
    private Long id;
    private Long ptTimes;
    private Long price;
    private Long dateOfUse;

    public OptionResultDto() {
    }

    public OptionResultDto(Long id, Long ptTimes, Long price, Long dateOfUse) {
        this.id = id;
        this.ptTimes = ptTimes;
        this.price = price;
        this.dateOfUse = dateOfUse;
    }

    public Long getId() {
        return id;
    }

    public Long getPtTimes() {
        return ptTimes;
    }

    public Long getPrice() {
        return price;
    }

    public Long getDateOfUse() {
        return dateOfUse;
    }
}
