package megatera.makaoGymbackEnd.dtos;

public class OrderDto {
    private Long productId;
    private String ptStartDate;
    //ToDo:회원 아이디 필요함(로그인 구현후)

    public OrderDto(Long productId, String ptStartDate) {
        this.productId = productId;
        this.ptStartDate = ptStartDate;
    }

    public Long getProductId() {
        return productId;
    }

    public String getPtStartDate() {
        return ptStartDate;
    }
}
