package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
