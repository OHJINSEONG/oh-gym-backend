package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
