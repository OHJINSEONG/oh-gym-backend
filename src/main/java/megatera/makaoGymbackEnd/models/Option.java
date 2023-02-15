package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.OptionResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Option {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private Long dateOfUse;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "pt_times"))
    private Count ptTimes;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price"))
    private Amount price;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Option() {
    }

    public Option(Long productId, Count ptTimes, Amount price, Long dateOfUse) {
        this.productId = productId;
        this.ptTimes = ptTimes;
        this.price = price;
        this.dateOfUse = dateOfUse;
    }

    public static Option fake(Long productId) {
        Count count = new Count(12L);
        Amount amount = new Amount(360000L);
        Long dateOfUse = 90L;

        return new Option(productId, count, amount, dateOfUse);
    }

    public Long productId() {
        return productId;
    }

    public Count ptTimes() {
        return ptTimes;
    }

    public Amount price() {
        return price;
    }

    public Long dateOfUse() {
        return dateOfUse;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Option.class &&
                Objects.equals(this.id, ((Option) other).id) &&
                Objects.equals(this.ptTimes, ((Option) other).ptTimes) &&
                Objects.equals(this.dateOfUse, ((Option) other).dateOfUse) &&
                Objects.equals(this.price, ((Option) other).price);
    }

    public OptionResultDto toDto() {
        return new OptionResultDto(id, ptTimes.value(), price.value(), dateOfUse);
    }
}
