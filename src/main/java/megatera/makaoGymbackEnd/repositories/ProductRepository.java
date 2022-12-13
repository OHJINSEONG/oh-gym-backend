package megatera.makaoGymbackEnd.repositories;

import megatera.makaoGymbackEnd.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
