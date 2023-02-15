package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.OrderDetailDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "ORDERS")
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private Long userId;

    private Long optionId;

    private String itemName;

    private Long totalPrice;

    private String consumerAddress;

    private String consumerGender;

    private String consumerAddressDetail;

    private String consumerBirthDate;

    private String consumerPhoneNumber;

    private String cunsumerName;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order() {
    }

    public Order(
            Long userId,
            Long productId,
            Long optionId,
            String itemName,
            Long totalPrice,
            String consumerAddress,
            String consumerGender,
            String consumerAddressDetail,
            String consumerBirthDate,
            String consumerPhoneNumber,
            String cunsumerName) {
        this.userId = userId;
        this.productId = productId;
        this.optionId = optionId;
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.consumerAddress = consumerAddress;
        this.consumerGender = consumerGender;
        this.consumerAddressDetail = consumerAddressDetail;
        this.consumerBirthDate = consumerBirthDate;
        this.consumerPhoneNumber = consumerPhoneNumber;
        this.cunsumerName = cunsumerName;
    }

    public static Order fake(Long productId) {
        Long userId = 1L;
        Long optionId = 1L;
        Long totalPrice = 100000L;
        String itemName = "피티";
        String consumerAddress = "서울";
        String consumerAddressDetail = "성수동";
        String consumerGender = "남자";
        String consumerBirthDate = "950828";
        String consumerPhoneNumber = "010-5239-8955";
        String consumerName = "오진성";

        return new Order(userId, productId, optionId, itemName, totalPrice, consumerAddress, consumerGender,
                consumerAddressDetail, consumerBirthDate, consumerPhoneNumber, consumerName);
    }

    public Long productId() {
        return productId;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Order.class) &&
                this.id.equals(((Order) other).id);
    }

    public Long id() {
        return id;
    }

    public Long optionId() {
        return optionId;
    }

    public Long userId() {
        return userId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public OrderDetailDto toDto() {
        return new OrderDetailDto(
                id,
                itemName,
                totalPrice,
                consumerAddress,
                consumerAddressDetail,
                cunsumerName,
                createdAt.toString()
        );
    }
}
