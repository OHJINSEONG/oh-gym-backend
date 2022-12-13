package megatera.makaoGymbackEnd.services;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.OptionDto;
import megatera.makaoGymbackEnd.models.Option;
import megatera.makaoGymbackEnd.repositories.OptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OptionService {
    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<OptionDto> list(Long productId) {
        List<Option> options = optionRepository.findAllByProductId(productId);

        return options.stream().map(Option::toDto).toList();
    }
}
