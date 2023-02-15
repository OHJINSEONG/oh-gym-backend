package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.OptionResultDto;
import megatera.makaoGymbackEnd.dtos.ProductDetailDto;
import megatera.makaoGymbackEnd.dtos.ProductDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private Title title;

    private Long trainerId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "type"))
    private Category type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(Title title, Long trainerId, Category type) {
        this.title = title;
        this.trainerId = trainerId;
        this.type = type;
    }

    public Product(Long id, Title title, Long trainerId, Category type) {
        this.id = id;
        this.title = title;
        this.trainerId = trainerId;
        this.type = type;
    }

    public static Product fake(Title title) {
        return new Product(1L, title, 1L, new Category("PT"));
    }

    public Long id() {
        return id;
    }

    public Title title() {
        return title;
    }

    public Long trainerId() {
        return trainerId;
    }

    public ProductDto toDto() {
        return new ProductDto(id, title.value(), type.value(), trainerId);
    }

    public Category type() {
        return type;
    }

    public ProductDetailDto toDetailDto(List<Option> options, List<Trainer> trainers) {
        List<OptionResultDto> optionResultDtos = options.stream().filter(option -> option.productId().equals(id)).map(Option::toDto).toList();

        Optional<Trainer> optionalTrainer = trainers.stream().filter(trainer -> trainer.id().equals(trainerId)).findFirst();

        return optionalTrainer.map(trainer -> new ProductDetailDto(id, title.value(), trainerId, type.value(), optionResultDtos, trainer.userName().value(), trainer.image()))
                .orElseGet(() -> new ProductDetailDto(id, title.value(), trainerId, type.value(), optionResultDtos, null, null));
    }
}
