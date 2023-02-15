package megatera.makaoGymbackEnd.dtos;

public class OptionDto {
    private Long ptTimes;
    private Long price;
    private Long dateOfUse;

    public OptionDto(Long ptTimes, Long price, Long dateOfUse) {
        this.ptTimes = ptTimes;
        this.price = price;
        this.dateOfUse = dateOfUse;
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
