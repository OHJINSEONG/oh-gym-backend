package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserId(Long userId);

    Optional<Notification> findByUserIdAndIdGreaterThan(Long userId, Long id);
}
