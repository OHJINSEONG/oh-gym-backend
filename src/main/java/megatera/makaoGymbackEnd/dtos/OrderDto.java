package megatera.makaoGymbackEnd.dtos;

public class OrderDto {
    private Long id;
    private Long productId;
    private String ptStartDate;

    public OrderDto(Long id, Long productId, String ptStartDate) {
        this.id = id;
        this.productId = productId;
        this.ptStartDate = ptStartDate;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getPtStartDate() {
        return ptStartDate;
    }
}
