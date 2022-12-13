package megatera.makaoGymbackEnd.models;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import megatera.makaoGymbackEnd.dtos.OptionDto;

@Entity
public class Option {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private Integer dateOfUse;

    private Integer ptTimes;

    private Integer price;

    public Option() {
    }

    public Option(Long productId, Integer ptTimes, Integer dateOfUse, Integer price) {
        this.productId = productId;
        this.ptTimes = ptTimes;
        this.dateOfUse = dateOfUse;
        this.price = price;
    }

    public Long productId() {
        return productId;
    }

    public Integer dateOfUse() {
        return dateOfUse;
    }

    public Integer ptTimes() {
        return ptTimes;
    }

    public Integer price() {
        return price;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Option.class &&
                Objects.equals(this.id, ((Option) other).id) &&
                Objects.equals(this.dateOfUse, ((Option) other).dateOfUse) &&
                Objects.equals(this.ptTimes, ((Option) other).ptTimes) &&
                Objects.equals(this.price, ((Option) other).price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfUse, ptTimes, price);
    }

    public OptionDto toDto() {
        return new OptionDto(id, ptTimes, dateOfUse, price);
    }
}
