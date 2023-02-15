package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LockerRepository extends JpaRepository<Locker,Long> {
    Optional<Locker> findByUserId(Long userId);
}
