package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.RequestResultDto;
import megatera.makaoGymbackEnd.dtos.SetDto;
import megatera.makaoGymbackEnd.dtos.SetResultDto;
import megatera.makaoGymbackEnd.models.Set;
import megatera.makaoGymbackEnd.repositories.RequestRepository;
import megatera.makaoGymbackEnd.repositories.SetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SetServiceTest {
    private  SetService setService;
    private  SetRepository setRepository;

    @BeforeEach
    void setup() {
        setRepository= mock(SetRepository.class);
        setService = new SetService(setRepository);
    }

    @Test
    void create() {
        Long exerciseId = 1L;

        SetResultDto setResultDto = setService.create(exerciseId);

        assertThat(setResultDto.getWeight()).isEqualTo(0L);
    }

    @Test
    void list() {
        Long exerciseId = 1L;

        given(setRepository.findAllByExerciseId(exerciseId)).willReturn(List.of(
                Set.fake(12L),
                Set.fake(12L),
                Set.fake(12L)
        ));

        List<SetResultDto> setResultDtos = setService.list(exerciseId);

        assertThat(setResultDtos).hasSize(3);
    }

    @Test
    void patch() {
        Long exerciseId = 1L;

        List<SetDto> setDtos = List.of(
                new SetDto(1L,1L,40L,20L)
        );

        given(setRepository.findAllByExerciseId(exerciseId)).willReturn(List.of(
                Set.fake(12L)
        ));

        List<SetResultDto> setResultDtos = setService.patch(exerciseId,setDtos);

        assertThat(setResultDtos.get(0).getWeight()).isEqualTo(40L);
        assertThat(setResultDtos.get(0).getReps()).isEqualTo(20L);
    }

    @Test
    void complete() {
        Long setId = 1L;


        given(setRepository.getReferenceById(setId)).willReturn(
                Set.fake(12L)
        );

        SetResultDto setResultDto = setService.complete(setId);

        assertThat(setResultDto.getStatus()).isEqualTo("COMPLETE");
    }
}