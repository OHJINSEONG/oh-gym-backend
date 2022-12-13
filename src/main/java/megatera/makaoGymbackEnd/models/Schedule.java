package megatera.makaoGymbackEnd.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    private Long trainerId;
}
