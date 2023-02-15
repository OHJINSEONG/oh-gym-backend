package megatera.makaoGymbackEnd.dtos;

public class OrderResultDto {
    private OrderDetailDto order;
    private ProductDetailDto productInformation;
    private OptionResultDto optionInformation;
    private PtTicketDto ptTicket;

    public OrderResultDto(OrderDetailDto order,ProductDetailDto productInformation, OptionResultDto optionInformation,PtTicketDto ptTicket) {
        this.order = order;
        this.productInformation = productInformation;
        this.optionInformation = optionInformation;
        this.ptTicket = ptTicket;
    }

    public OrderDetailDto getOrder() {
        return order;
    }

    public ProductDetailDto getProductInformation() {
        return productInformation;
    }

    public OptionResultDto getOptionInformation() {
        return optionInformation;
    }

    public PtTicketDto getPtTicket() {
        return ptTicket;
    }
}
