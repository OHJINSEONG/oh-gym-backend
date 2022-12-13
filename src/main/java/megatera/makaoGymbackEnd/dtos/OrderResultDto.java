package megatera.makaoGymbackEnd.dtos;

public class OrderResultDto {
    private Long id;
    private Long productId;
    private String ptStartDate;

    public OrderResultDto(Long id,Long productId, String ptStartDate) {
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
