package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.DiaryDto;
import megatera.makaoGymbackEnd.dtos.DiaryResultDto;
import megatera.makaoGymbackEnd.models.Diary;
import megatera.makaoGymbackEnd.models.Exercise;
import megatera.makaoGymbackEnd.models.Name;
import megatera.makaoGymbackEnd.models.Set;
import megatera.makaoGymbackEnd.repositories.DiaryRepository;
import megatera.makaoGymbackEnd.repositories.ExerciseRepository;
import megatera.makaoGymbackEnd.repositories.SetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DiaryServiceTest {
    private DiaryService diaryService;
    private DiaryRepository diaryRepository;
    private ExerciseRepository exerciseRepository;
    private SetRepository setRepository;

    @BeforeEach
    void setup() {
        exerciseRepository = mock(ExerciseRepository.class);
        setRepository = mock(SetRepository.class);
        diaryRepository = mock(DiaryRepository.class);
        diaryService = new DiaryService(diaryRepository, exerciseRepository, setRepository);
    }

    @Test
    void list() {
        given(diaryRepository.findAllByUserId(any())).willReturn(List.of(
                Diary.fake(LocalDate.now())
        ));

        given(exerciseRepository.findAll()).willReturn(List.of(
                Exercise.fake(new Name("풀업"))
        ));

        given(setRepository.findAll()).willReturn(List.of(
                Set.fake(12L),
                Set.fake(30L)
        ));

        List<DiaryResultDto> diaryResultDtos = diaryService.list(any());

        assertThat(diaryResultDtos.get(0).getExerciseInformations().get(0).getSets()).hasSize(2);
        assertThat(diaryResultDtos.get(0).getExerciseInformations().get(0).getExercise().getName()).isEqualTo("풀업");
    }

    @Test
    void find() {
        Long userId = 1L;
        String date = "2022-12-09";

        given(diaryRepository.findByDateAndUserId(LocalDate.parse(date), userId)).willReturn(
                Optional.of(Diary.fake(LocalDate.now()))
        );

        given(exerciseRepository.findAll()).willReturn(List.of(
                Exercise.fake(new Name("풀업"))
        ));

        given(setRepository.findAll()).willReturn(List.of(
                Set.fake(12L),
                Set.fake(30L)
        ));

        DiaryResultDto diaryResultDto = diaryService.find(date, userId);

        assertThat(diaryResultDto.getExerciseInformations().get(0).getSets()).hasSize(2);
        assertThat(diaryResultDto.getExerciseInformations().get(0).getExercise().getName()).isEqualTo("풀업");
    }

    @Test
    void create() {
        Long userId = 1L;
        String date = "2022-12-09";

        given(diaryRepository.findByDateAndUserId(any(), any())).willReturn(
                Optional.empty()
        );

        DiaryDto diaryDto = diaryService.create(date, userId);

        assertThat(diaryDto.getDate()).isEqualTo("2022-12-09");
    }

    @Test
    void complete() {
        Long diaryId = 1L;
        String memo = "오늘 운동 굿";
        String time = "00:00:02";

        given(diaryRepository.getReferenceById(diaryId)).willReturn(
                Diary.fake(LocalDate.now())
        );

        given(exerciseRepository.findAll()).willReturn(List.of(
                Exercise.fake(new Name("풀업"))
        ));

        given(setRepository.findAll()).willReturn(List.of(
                Set.fake(12L),
                Set.fake(30L)
        ));

        DiaryResultDto diaryResultDto = diaryService.complete(diaryId, memo, time);

        assertThat(diaryResultDto.getDiary().getTime()).isEqualTo("00:00:02");
        assertThat(diaryResultDto.getDiary().getMemo()).isEqualTo("오늘 운동 굿");
        assertThat(diaryResultDto.getDiary().getStatus()).isEqualTo("COMPLETE");
    }

    @Test
    void findById() {
        Long diaryId = 1L;

        given(diaryRepository.getReferenceById(diaryId)).willReturn(
                Diary.fake(LocalDate.now())
        );

        given(exerciseRepository.findAll()).willReturn(List.of(
                Exercise.fake(new Name("풀업"))
        ));

        given(setRepository.findAll()).willReturn(List.of(
                Set.fake(12L),
                Set.fake(30L)
        ));

        DiaryResultDto diaryResultDto = diaryService.findById(diaryId);

        assertThat(diaryResultDto.getDiary().getStatus()).isEqualTo("CREATED");
    }
}