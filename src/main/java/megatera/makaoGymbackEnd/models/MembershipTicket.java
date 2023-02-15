package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.MembershipTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.MembershipTicketDto;
import megatera.makaoGymbackEnd.dtos.ProductDto;
import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class MembershipTicket {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long productId;

    private Long orderId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "membership_period"))
    private Period membershipPeriod;

    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public MembershipTicket() {
    }

    public MembershipTicket(Long userId, Long productId, Period membershipPeriod, Long orderId) {
        this.userId = userId;
        this.productId = productId;
        this.membershipPeriod = membershipPeriod;
        this.orderId = orderId;
    }

    public static MembershipTicket fake(Long userId) {
        Long productId = 1L;
        Long orderId = 1L;

        MembershipTicket membershipTicket = new MembershipTicket(userId,productId,new Period(90L),orderId);

        membershipTicket.unused();

        return membershipTicket;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == MembershipTicket.class &&
                Objects.equals(this.id, ((MembershipTicket) other).id) &&
                Objects.equals(this.membershipPeriod, ((MembershipTicket) other).membershipPeriod);
    }

    public void setStartDate(String startDate) {
        membershipPeriod.setStartTime(LocalDate.parse(startDate));
    }

    public void used() {
        this.status.toUsed();
    }

    public MembershipTicketDto toDto() {
        if (status.value().equals("UNUSED")) {
            return new MembershipTicketDto(id, membershipPeriod.value(), status.value());
        }
        return new MembershipTicketDto(id, membershipPeriod.startDate().toString(), membershipPeriod.endDate().toString(), status.value(), membershipPeriod.value());
    }

    public void unused() {
        this.status = new Status("UNUSED");
    }

    public Long productId() {
        return productId;
    }

    public boolean inUse() {
        return this.status.value().equals("INUSE");
    }

    public MembershipTicketDetailDto toDetailDto(List<Product> products, List<Trainer> trainers) {
        ProductDto productDto = products.stream().filter(product -> product.id().equals(productId)).findFirst()
                .map(Product::toDto).orElseGet(() -> new ProductDto(null, null, null, null));

        TrainerResultDto trainerResultDto = trainers.stream().filter(trainer -> trainer.id().equals(productDto.getTrainerId())).findFirst()
                .map(Trainer::toDto).orElseGet(() -> new TrainerResultDto(null, null, null, null, null, null, null, null, null, null));

        return new MembershipTicketDetailDto(
                toDto().getId(),
                toDto().getStartDate(),
                toDto().getEndDate(),
                toDto().getUseOfDate(),
                toDto().getStatus(),
                trainerResultDto.getImage(),
                trainerResultDto.getUserName(),
                productDto.getTrainerId(),
                productDto.getType()
        );
    }
}
