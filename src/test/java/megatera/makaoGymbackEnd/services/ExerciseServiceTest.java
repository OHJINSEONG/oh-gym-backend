package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.DiaryResultDto;
import megatera.makaoGymbackEnd.dtos.ExerciseDto;
import megatera.makaoGymbackEnd.dtos.ExerciseResultDto;
import megatera.makaoGymbackEnd.models.Diary;
import megatera.makaoGymbackEnd.models.Exercise;
import megatera.makaoGymbackEnd.models.Name;
import megatera.makaoGymbackEnd.models.Set;
import megatera.makaoGymbackEnd.repositories.ExerciseRepository;
import megatera.makaoGymbackEnd.repositories.SetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ExerciseServiceTest {
    private ExerciseService exerciseService;

    private ExerciseRepository exerciseRepository;

    private SetRepository setRepository;

    @BeforeEach
    void setup() {
        exerciseRepository = mock(ExerciseRepository.class);
        setRepository = mock(SetRepository.class);
        exerciseService = new ExerciseService(exerciseRepository, setRepository);
    }

    @Test
    void list() {
        given(exerciseRepository.findAllByDiaryId(any())).willReturn(List.of(
                Exercise.fake(new Name("풀업"))
        ));

        given(setRepository.findAll()).willReturn(List.of(
                Set.fake(12L),
                Set.fake(30L)
        ));

        List<ExerciseResultDto> exerciseResultDtos = exerciseService.list(any());

        assertThat(exerciseResultDtos.get(0).getSets()).hasSize(2);
        assertThat(exerciseResultDtos.get(0).getExercise().getName()).isEqualTo("풀업");
    }

    @Test
    void create() {
        String name = "풀업";
        String type = "등";
        Long diaryId = 1L;

        ExerciseDto exerciseDto = exerciseService.create(name,type,diaryId);

        assertThat(exerciseDto.getName()).isEqualTo("풀업");
    }

    @Test
    void find() {
        given(exerciseRepository.getReferenceById(any())).willReturn(
                Exercise.fake(new Name("풀업"))
        );

        given(setRepository.findAll()).willReturn(List.of(
                Set.fake(12L),
                Set.fake(30L)
        ));

        ExerciseResultDto exerciseResultDto = exerciseService.find(any());

        assertThat(exerciseResultDto.getSets()).hasSize(2);
        assertThat(exerciseResultDto.getExercise().getName()).isEqualTo("풀업");
        assertThat(exerciseResultDto.getExercise().getStatus()).isEqualTo("CREATED");
    }

    @Test
    void complete() {
        given(exerciseRepository.getReferenceById(any())).willReturn(
                Exercise.fake(new Name("풀업"))
        );

        given(setRepository.findAll()).willReturn(List.of(
                Set.fake(12L),
                Set.fake(30L)
        ));

        ExerciseResultDto exerciseResultDto = exerciseService.complete(any());

        assertThat(exerciseResultDto.getSets()).hasSize(2);
        assertThat(exerciseResultDto.getExercise().getName()).isEqualTo("풀업");
        assertThat(exerciseResultDto.getExercise().getStatus()).isEqualTo("COMPLETE");
    }

}