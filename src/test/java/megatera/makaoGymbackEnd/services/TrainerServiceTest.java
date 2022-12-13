package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import megatera.makaoGymbackEnd.models.Trainer;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TrainerServiceTest {
    private TrainerService trainerService;

    private TrainerRepository trainerRepository;

    @BeforeEach
    void setup(){
        trainerRepository = mock(TrainerRepository.class);
        trainerService = new TrainerService(trainerRepository);
    }

    @Test
    void find(){
        Long id = 1L;

        given(trainerRepository.getReferenceById(id)).willReturn(Trainer.fake(new UserName("오진성")));

        TrainerResultDto trainerResultDto = trainerService.find(id);

        assertThat(trainerResultDto.getUserName()).isEqualTo("오진성");
    }
}
