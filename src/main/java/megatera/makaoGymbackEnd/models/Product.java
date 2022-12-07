package megatera.makaoGymbackEnd.models;

import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import megatera.makaoGymbackEnd.dtos.ProductDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Embedded
    private UserName trainer;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "dateOption"))
    private Option dateOfUse;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "ptOption"))
    private Option ptTimes;

    private String timeOfPt;

    private String dayOfWeek;

    private int price;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public Product() {
    }

    public Product(String title, UserName trainer, Option dateOfUse, Option ptTimes, String timeOfPt, String dayOfWeek, int price) {
        this.title = title;
        this.trainer = trainer;
        this.dateOfUse = dateOfUse;
        this.ptTimes = ptTimes;
        this.timeOfPt = timeOfPt;
        this.dayOfWeek = dayOfWeek;
        this.price = price;
    }

    public static Product fake(String title) {
        UserName trainer = new UserName("오진욱");
        Option dateOfUse = new Option(90);
        Option ptTimes = new Option(12);
        String timeOfPt = "11:00";
        String dayOfWeek = "월 화 수";
        int price = 360000;

        return new Product(title, trainer, dateOfUse, ptTimes, timeOfPt, dayOfWeek, price);
    }

    public String title() {
        return title;
    }

    public UserName trainer() {
        return trainer;
    }

    public Option dateOfUse() {
        return dateOfUse;
    }

    public Option ptTimes() {
        return ptTimes;
    }

    public String timeOfPt() {
        return timeOfPt;
    }

    public String dayOfWeek() {
        return dayOfWeek;
    }

    public int price() {
        return price;
    }

    public ProductDto toDto() {
        Integer date = dateOfUse == null ? null : dateOfUse.value();
        Integer pt = ptTimes == null ? null : ptTimes.value();

        return new ProductDto(id, title, trainer.value(), date, pt, timeOfPt, dayOfWeek, price);
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass().equals(Product.class) &&
                this.id.equals(((Product) other).id);
    }
}
