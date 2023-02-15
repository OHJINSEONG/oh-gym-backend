package megatera.makaoGymbackEnd.dtos;

import java.time.LocalDateTime;

public class OrderDetailDto {
    private Long id;
    private String itemName;
    private Long totalPrice;
    private String consumerAddress;
    private String consumerAddressDetail;
    private String cunsumerName;
    private String orderTime;

    public OrderDetailDto() {
    }

    public OrderDetailDto(
            Long id,
            String itemName,
            Long totalPrice,
            String consumerAddress,
            String consumerAddressDetail,
            String cunsumerName,
            String orderTime
    ) {
        this.id = id;
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.consumerAddress = consumerAddress;
        this.consumerAddressDetail = consumerAddressDetail;
        this.cunsumerName = cunsumerName;
        this.orderTime = orderTime;
    }

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public String getConsumerAddress() {
        return consumerAddress;
    }

    public String getConsumerAddressDetail() {
        return consumerAddressDetail;
    }

    public String getCunsumerName() {
        return cunsumerName;
    }

    public String getOrderTime() {
        return orderTime;
    }
}
