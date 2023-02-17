package megatera.makaoGymbackEnd.dtos;

public class OrderRegisterDto {
    private Long productId;
    private Long optionId;
    private String itemName;
    private Long totalPrice;
    private String cunsumerName;
    private String consumerGender;
    private String consumerBirthDate;
    private String consumerPhoneNumber;
    private String consumerAddress;
    private String consumerAddressDetail;
    private String type;

    public OrderRegisterDto() {
    }

    public OrderRegisterDto(
            Long productId,
            Long optionId,
            String itemName,
            Long totalPrice,
            String cunsumerName,
            String consumerGender,
            String consumerBirthDate,
            String consumerPhoneNumber,
            String consumerAddress,
            String consumerAddressDetail,
            String type
    ) {
        this.productId = productId;
        this.optionId = optionId;
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.cunsumerName = cunsumerName;
        this.consumerGender = consumerGender;
        this.consumerBirthDate = consumerBirthDate;
        this.consumerPhoneNumber = consumerPhoneNumber;
        this.consumerAddress = consumerAddress;
        this.consumerAddressDetail = consumerAddressDetail;
        this.type = type;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public String getItemName() {
        return itemName;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public String getCunsumerName() {
        return cunsumerName;
    }

    public String getConsumerGender() {
        return consumerGender;
    }

    public String getConsumerBirthDate() {
        return consumerBirthDate;
    }

    public String getConsumerPhoneNumber() {
        return consumerPhoneNumber;
    }

    public String getConsumerAddress() {
        return consumerAddress;
    }

    public String getConsumerAddressDetail() {
        return consumerAddressDetail;
    }

    public String getType() {
        return type;
    }
}
