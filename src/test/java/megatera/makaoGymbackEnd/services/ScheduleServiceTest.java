package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.ScheduleDetailDto;
import megatera.makaoGymbackEnd.dtos.ScheduleDto;
import megatera.makaoGymbackEnd.dtos.WorkDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScheduleServiceTest {
    @Test
    void createSchedule() {
        ScheduleService scheduleService = new ScheduleService();

        List<LectureDto> lectureDtos = List.of(
                new LectureDto(1L, "오진성", "CREATED", "2022-12-10", "11:00"),
                new LectureDto(2L, "오진성", "CREATED", "2022-12-11", "11:00"),
                new LectureDto(3L, "오진성", "CREATED", "2022-12-12", "11:00")
        );

        List<WorkDto> workDtos = List.of(
                new WorkDto(1L, "CREATED", "2022-12-10", "10:00", "14:00"),
                new WorkDto(3L, "CREATED", "2022-12-10", "15:00", "18:00")
        );

        ScheduleDto scheduleDto = scheduleService.createSchedule(lectureDtos, workDtos);

        assertThat(scheduleDto.getEmptySchedules()).hasSize(6);
    }

    @Test
    void scheduleList() {
        ScheduleService scheduleService = new ScheduleService();

        List<LectureDto> lectureDtos = List.of(
                new LectureDto(1L, "오진성", "CREATED", "2022-12-10", "11:00"),
                new LectureDto(2L, "오진성", "CREATED", "2022-12-11", "11:00"),
                new LectureDto(3L, "오진성", "CREATED", "2022-12-12", "11:00")
        );

        List<WorkDto> workDtos = List.of(
                new WorkDto(1L, "CREATED", "2022-12-10", "10:00", "14:00"),
                new WorkDto(2L, "CREATED", "2022-12-10", "10:00", "14:00"),
                new WorkDto(3L, "CREATED", "2022-12-10", "10:00", "14:00")
        );

        List<ScheduleDetailDto> scheduleDetailDtos = scheduleService.scheduleList(lectureDtos, workDtos);

        assertThat(scheduleDetailDtos).hasSize(3);
    }
}