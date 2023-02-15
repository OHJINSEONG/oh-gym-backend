package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.OptionDto;
import megatera.makaoGymbackEnd.dtos.OptionResultDto;
import megatera.makaoGymbackEnd.models.Amount;
import megatera.makaoGymbackEnd.models.Count;
import megatera.makaoGymbackEnd.models.Option;
import megatera.makaoGymbackEnd.repositories.OptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OptionService {
    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<OptionResultDto> list(Long productId) {
        List<Option> options = optionRepository.findAllByProductId(productId);

        return options.stream().map(Option::toDto).toList();
    }

    public List<OptionResultDto> create(Long productId, List<OptionDto> options) {
        List<OptionResultDto> optionResultDtos = new ArrayList<>();
        for (OptionDto optionDto : options) {
            Option option = new Option(
                    productId,
                    new Count(optionDto.getPtTimes()),
                    new Amount(optionDto.getPrice()),
                    optionDto.getDateOfUse()
            );
            optionRepository.save(option);

            optionResultDtos.add(option.toDto());
        }

        return optionResultDtos;
    }

    public OptionResultDto find(Long optionId) {
        return optionRepository.getReferenceById(optionId).toDto();
    }
}
