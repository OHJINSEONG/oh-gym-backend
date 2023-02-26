package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.WorkDto;
import megatera.makaoGymbackEnd.models.Work;
import megatera.makaoGymbackEnd.repositories.WorkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class WorkServiceTest {
    private WorkRepository workRepository;
    private WorkService workService;

    @BeforeEach
    void setup() {
        workRepository = mock(WorkRepository.class);
        workService = new WorkService(workRepository);
    }

    @Test
    void findWithDate() {
        Long trainerId = 1L;
        LocalDate date = LocalDate.now();

        given(workRepository.findAllByTrainerIdAndDate(trainerId, date)).willReturn(List.of(
                Work.fake(trainerId)
        ));

        List<WorkDto> workDtos = workService.findWithDate(trainerId, date);

        assertThat(workDtos.get(0).getStartTime()).isEqualTo("11:00");
    }

//    @Test
//    void createWork() {
//        Long trainerId = 1L;
//        Integer countOfWeek = 4;
//        String startDate = "2022-12-10";
//        String startTime = "11:00";
//        String endTime = "16:00";
//
//        List<Integer> dayOfWeeks = List.of(
//                2, 4, 6
//        );
//
//        DayOfWeek dayOfWeek1 = LocalDate.parse("2022-12-10").getDayOfWeek();
//        assertThat(dayOfWeek1.getValue()).isEqualTo(6);
//
//        List<WorkDto> workDtos = workService.createWork(trainerId, startDate, countOfWeek, startTime, endTime, dayOfWeeks);
//
//        DayOfWeek dayOfWeek2 = LocalDate.parse("2022-12-12").getDayOfWeek();
//        assertThat(dayOfWeek2.getValue()).isEqualTo(1);
//
//        DayOfWeek dayOfWeek3 = LocalDate.parse("2022-12-14").getDayOfWeek();
//        assertThat(dayOfWeek3.getValue()).isEqualTo(3);
//
//        DayOfWeek dayOfWeek4 = LocalDate.parse("2022-12-16").getDayOfWeek();
//        assertThat(dayOfWeek4.getValue()).isEqualTo(5);
//
//        DayOfWeek dayOfWeek5 = LocalDate.parse("2022-12-19").getDayOfWeek();
//        assertThat(dayOfWeek5.getValue()).isEqualTo(1);
//
//        DayOfWeek dayOfWeek6 = LocalDate.parse("2022-12-21").getDayOfWeek();
//        assertThat(dayOfWeek6.getValue()).isEqualTo(3);
//
//        DayOfWeek dayOfWeek7 = LocalDate.parse("2022-12-23").getDayOfWeek();
//        assertThat(dayOfWeek7.getValue()).isEqualTo(5);
//
//        DayOfWeek dayOfWeek8 = LocalDate.parse("2022-12-26").getDayOfWeek();
//        assertThat(dayOfWeek8.getValue()).isEqualTo(1);
//
//        DayOfWeek dayOfWeek9 = LocalDate.parse("2022-12-28").getDayOfWeek();
//        assertThat(dayOfWeek9.getValue()).isEqualTo(3);
//
//        DayOfWeek dayOfWeek10 = LocalDate.parse("2022-12-30").getDayOfWeek();
//        assertThat(dayOfWeek10.getValue()).isEqualTo(5);
//
//        assertThat(workDtos.get(0).getDate()).isEqualTo("2022-12-12");
//        assertThat(workDtos.get(1).getDate()).isEqualTo("2022-12-14");
//        assertThat(workDtos.get(2).getDate()).isEqualTo("2022-12-16");
//        assertThat(workDtos.get(3).getDate()).isEqualTo("2022-12-19");
//        assertThat(workDtos.get(4).getDate()).isEqualTo("2022-12-21");
//        assertThat(workDtos.get(5).getDate()).isEqualTo("2022-12-23");
//        assertThat(workDtos.get(6).getDate()).isEqualTo("2022-12-26");
//        assertThat(workDtos.get(7).getDate()).isEqualTo("2022-12-28");
//    }
}