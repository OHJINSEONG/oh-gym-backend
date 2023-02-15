package megatera.makaoGymbackEnd.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
@Embeddable
public class Category {
    @Column(name = "category")
    private String value;

    public Category() {
    }

    public Category(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "Category(" + value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == Category.class &&
                Objects.equals(this.value, ((Category) other).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
