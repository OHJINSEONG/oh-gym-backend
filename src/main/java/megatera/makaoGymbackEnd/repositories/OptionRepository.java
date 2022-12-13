package megatera.makaoGymbackEnd.repositories;

import java.util.List;
import megatera.makaoGymbackEnd.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findAllByProductId(Long productId);
}
