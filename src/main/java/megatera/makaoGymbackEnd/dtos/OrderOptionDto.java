package megatera.makaoGymbackEnd.dtos;

public class OrderOptionDto {
    private Long id;
    private Long ptTimes;
    private Long price;
    private Long dateOfUse;
    private String type;

    public OrderOptionDto(Long id, Long ptTimes, Long price, Long dateOfUse,String type) {
        this.id = id;
        this.ptTimes = ptTimes;
        this.price = price;
        this.dateOfUse = dateOfUse;
        this.type = type;
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

    public String getType() {
        return type;
    }
}
