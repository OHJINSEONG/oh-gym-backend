package megatera.makaoGymbackEnd.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "PERSON")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private UserName userName;

    private String name;
}
