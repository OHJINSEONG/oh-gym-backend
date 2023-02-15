package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.ProductDto;
import megatera.makaoGymbackEnd.dtos.PtTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.PtTicketDto;
import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class PtTicket {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long trainerId;

    private Long orderId;

    private Long productId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "pt_times"))
    private Count ptTimes;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "period_of_use"))
    private Period periodOfUse;

    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public PtTicket() {
    }

    public PtTicket(Long userId, Long trainerId, Long orderId, Period periodOfUse, Count ptTimes, Long productId) {
        this.userId = userId;
        this.trainerId = trainerId;
        this.orderId = orderId;
        this.periodOfUse = periodOfUse;
        this.ptTimes = ptTimes;
        this.productId = productId;
    }

    public static PtTicket fake(Long userId) {
        Long productId = 1L;
        Long orderId = 1L;
        Long trainerId = 1L;

        PtTicket ptTicket = new PtTicket(userId, trainerId, orderId, new Period(90L), new Count(12L), productId);

        ptTicket.unUsed();

        return ptTicket;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == PtTicket.class &&
                Objects.equals(this.id, ((PtTicket) other).id);
    }

    public void setStartDate(String startDate) {
        periodOfUse.setStartTime(LocalDate.parse(startDate));
    }

    public void used() {
        this.status.toUsed();
    }

    public void unUsed() {
        this.status = new Status("UNUSED");
    }

    public PtTicketDto toDto() {
        if (status.value().equals("UNUSED")) {
            return new PtTicketDto(id, periodOfUse.value(), ptTimes.value(), status.value());
        }
        return new PtTicketDto(id, periodOfUse.startDate().toString(), periodOfUse.endDate().toString(), ptTimes.value(), status.value(), periodOfUse.value());
    }

    public Long productId() {
        return productId;
    }

    public boolean inUse() {
        return this.status.value().equals("INUSE");
    }

    public void countPt() {
        this.ptTimes.check();
    }

    public void cancelPt() {
        this.ptTimes.cancel();
    }

    public Long userId() {
        return userId;
    }

    public PtTicketDetailDto toDetailDto(List<Product> products, List<Trainer> trainers) {
        ProductDto productDto = products.stream().filter(product -> product.id().equals(productId)).findFirst()
                .map(Product::toDto).orElseGet(() -> new ProductDto(null, null, null, null));

        TrainerResultDto trainerResultDto = trainers.stream().filter(trainer -> trainer.id().equals(trainerId)).findFirst()
                .map(Trainer::toDto).orElseGet(() -> new TrainerResultDto(null, null, null, null, null, null, null, null, null, null));

        return new PtTicketDetailDto(
                toDto().getId(),
                toDto().getStartDate(),
                toDto().getEndDate(),
                toDto().getPtTimes(),
                toDto().getPeriodOfUse(),
                toDto().getStatus(),
                trainerResultDto.getImage(),
                trainerResultDto.getUserName(),
                productDto.getTrainerId(),
                productDto.getType()
        );
    }

    public Status status() {
        return status;
    }

    public Count ptTimes() {
        return ptTimes;
    }
}
