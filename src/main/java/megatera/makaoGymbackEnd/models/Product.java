package megatera.makaoGymbackEnd.models;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import megatera.makaoGymbackEnd.dtos.OptionDto;
import megatera.makaoGymbackEnd.dtos.ProductDetailDto;
import megatera.makaoGymbackEnd.dtos.ProductDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Long trainerId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(String title, Long trainerId) {
        this.title = title;
        this.trainerId = trainerId;
    }

    public static Product fake(String title) {
        return new Product(title, 1L);
    }

    public Long id() {
        return id;
    }

    public String title() {
        return title;
    }

    public Long trainerId() {
        return trainerId;
    }

    public ProductDto toDto() {
        return new ProductDto(id, title, trainerId);
    }

    public ProductDetailDto toDetailDto(List<OptionDto> optionDtos) {
        return new ProductDetailDto(id, title, trainerId, optionDtos);
    }
}
