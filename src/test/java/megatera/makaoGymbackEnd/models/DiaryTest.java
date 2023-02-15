package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.DiaryResultDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiaryTest {
    @Test
    void setTime() {
        Diary diary = Diary.fake(LocalDate.now());

        diary.setTime("00:00:10");

        assertThat(diary.time()).isEqualTo("00:00:10");
    }

    @Test
    void setMemo() {
        Diary diary = Diary.fake(LocalDate.now());

        diary.setMemo("오운완");

        assertThat(diary.memo()).isEqualTo("오운완");
    }

    @Test
    void complete() {
        Diary diary = Diary.fake(LocalDate.now());

        diary.complete();

        assertThat(diary.status().value()).isEqualTo("COMPLETE");
    }

    @Test
    void toResultDto() {
        List<Exercise>  exercises = List.of(
                Exercise.fake(new Name("풀업"))
        );

        List<Set> sets = List.of(
                Set.fake(1L),
                Set.fake(1L),
                Set.fake(1L)
        );

        Diary diary = Diary.fake(LocalDate.now());

        DiaryResultDto diaryResultDto = diary.toResultDto(exercises,sets);

        assertThat(diaryResultDto.getExerciseInformations().get(0).getSets()).hasSize(3);
    }
}
