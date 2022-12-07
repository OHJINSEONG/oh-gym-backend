package megatera.makaoGymbackEnd.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import megatera.makaoGymbackEnd.dtos.OrderResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "LICENSE")
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private String ptStartDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order() {
    }

    public Order(String ptStartDate, Long productId) {
        this.ptStartDate = ptStartDate;
        this.productId = productId;
    }

    public static Order fake(Long productId) {
        String ptStartDate = "2022/12/06";

        return new Order(ptStartDate, productId);
    }

    public Long getProductId() {
        return productId;
    }

    public String getPtStartDate() {
        return ptStartDate;
    }

    public OrderResultDto toDto() {
        return new OrderResultDto(id, productId, ptStartDate);
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Order.class) &&
                this.id.equals(((Order) other).id);
    }
}
