package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import megatera.makaoGymbackEnd.models.Trainer;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AdminTrainerServiceTest {
    private PtTicketService ptTicketService;
    private TrainerRepository trainerRepository;
    private UserService userService;
    private DiaryService diaryService;
    private AdminTrainerService adminTrainerService;
    private ChattingRoomService chattingRoomService;
    private RequestService requestService;

    @BeforeEach
    void setup() {
        ptTicketService = mock(PtTicketService.class);
        chattingRoomService = mock(ChattingRoomService.class);
        trainerRepository = mock(TrainerRepository.class);
        userService = mock(UserService.class);
        requestService = mock(RequestService.class);
        diaryService = mock(DiaryService.class);
        adminTrainerService = new AdminTrainerService(ptTicketService, trainerRepository, userService, diaryService, chattingRoomService, requestService);
    }

    @Test
    void create() {
        String name = "오진성";
        String userName = "오진성";
        String image = "이미지";

        given(adminTrainerService.create(name, userName, image)).willReturn(
                Trainer.fake(new UserName(userName)).toDto()
        );

        TrainerResultDto trainerResultDto = adminTrainerService.create(name, userName, image);

        assertThat(trainerResultDto.getUserName()).isEqualTo("오진성");
    }
}