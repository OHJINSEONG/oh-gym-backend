package megatera.makaoGymbackEnd.models;

import java.util.Objects;

public class KakaoPayAmount {
    private Integer total;

    private Integer tax_free;

    private Integer vat;

    private Integer point;

    private Integer discount;

    private Integer green_deposit;

    public KakaoPayAmount() {
    }

    public KakaoPayAmount(
            Integer total,
            Integer tax_free,
            Integer vat,
            Integer point,
            Integer discount,
            Integer green_deposit) {
        this.total = total;
        this.tax_free = tax_free;
        this.vat = vat;
        this.point = point;
        this.discount = discount;
        this.green_deposit = green_deposit;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        KakaoPayAmount kakaoPayAmount = (KakaoPayAmount) other;
        return Objects.equals(total, kakaoPayAmount.total) &&
                Objects.equals(tax_free, kakaoPayAmount.tax_free) &&
                Objects.equals(vat, kakaoPayAmount.vat) &&
                Objects.equals(point, kakaoPayAmount.point) &&
                Objects.equals(discount, kakaoPayAmount.discount) &&
                Objects.equals(green_deposit, kakaoPayAmount.green_deposit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, tax_free, vat, point, discount, green_deposit);
    }

    @Override
    public String toString() {
        return "KakaoPayAmount{" +
                "total=" + total +
                ", tax_free=" + tax_free +
                ", vat=" + vat +
                ", point=" + point +
                ", discount=" + discount +
                ", green_deposit=" + green_deposit +
                '}';
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTax_free() {
        return tax_free;
    }

    public Integer getVat() {
        return vat;
    }

    public Integer getPoint() {
        return point;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Integer getGreen_deposit() {
        return green_deposit;
    }
}
