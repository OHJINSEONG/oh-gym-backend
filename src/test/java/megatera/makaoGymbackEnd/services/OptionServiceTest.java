package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.OptionDto;
import megatera.makaoGymbackEnd.dtos.OptionResultDto;
import megatera.makaoGymbackEnd.models.Option;
import megatera.makaoGymbackEnd.repositories.OptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OptionServiceTest {
    private OptionService optionService;
    private OptionRepository optionRepository;

    @BeforeEach
    void setup() {
        optionRepository = mock(OptionRepository.class);
        optionService = new OptionService(optionRepository);
    }

    @Test
    void list() {
        Long productId = 1L;

        given(optionRepository.findAllByProductId(productId)).willReturn(List.of(
                Option.fake(productId),
                Option.fake(productId),
                Option.fake(productId)
        ));

        List<OptionResultDto> optionResultDtos = optionService.list(productId);

        assertThat(optionResultDtos).hasSize(3);
    }

    @Test
    void create() {
        Long productId = 1L;

        List<OptionDto> optionsDtos = List.of(
                new OptionDto(12L, 360000L, 90L),
                new OptionDto(30L, 720000L, 180L),
                new OptionDto(60L, 1080000L, 3600L)
        );

        List<OptionResultDto> optionResultDtos = optionService.create(productId, optionsDtos);

        assertThat(optionResultDtos).hasSize(3);
    }

    @Test
    void find() {
        given(optionRepository.getReferenceById(any())).willReturn(
                Option.fake(1L)
        );

        OptionResultDto optionResultDto = optionService.find(any());

        assertThat(optionResultDto.getDateOfUse()).isEqualTo(90L);
    }
}