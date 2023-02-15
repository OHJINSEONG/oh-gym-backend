package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.ChattingRoomDto;
import megatera.makaoGymbackEnd.models.ChattingRoom;
import megatera.makaoGymbackEnd.models.Trainer;
import megatera.makaoGymbackEnd.models.User;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.repositories.ChattingRoomRepository;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import megatera.makaoGymbackEnd.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ChattingRoomServiceTest {
    private ChattingRoomService chattingRoomService;

    private ChattingRoomRepository chattingRoomRepository;

    private UserRepository userRepository;

    private TrainerRepository trainerRepository;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        trainerRepository = mock(TrainerRepository.class);
        chattingRoomRepository = mock(ChattingRoomRepository.class);
        chattingRoomService = new ChattingRoomService(chattingRoomRepository, userRepository, trainerRepository);
    }

    @Test
    void find() {
        given(chattingRoomRepository.findByTrainerIdAndUserId(any(), any())).willReturn(
                Optional.of(ChattingRoom.fake(new UserName("오진성")))
        );

        Optional<ChattingRoom> optionalChattingRoom = chattingRoomService.find(any(), any());

        assertThat(optionalChattingRoom.get().toDto().getUserName()).isEqualTo("오진성");

    }

    @Test
    void create() {
        Long userId = 1L;
        Long trainerId = 2L;

        given(userRepository.getReferenceById(userId)).willReturn(
                User.fake(new UserName("오진성"))
        );

        given(trainerRepository.getReferenceById(trainerId)).willReturn(
                Trainer.fake(new UserName("배준형"))
        );

        ChattingRoomDto chattingRoomDto = chattingRoomService.create(trainerId, userId);

        assertThat(chattingRoomDto.getUserName()).isEqualTo("오진성");
        assertThat(chattingRoomDto.getTrainerName()).isEqualTo("배준형");
    }

    @Test
    void list() {
        Long userId = 1L;

        given(chattingRoomRepository.findAllByUserId(userId)).willReturn(List.of(
                ChattingRoom.fake(new UserName("오진성")),
                ChattingRoom.fake(new UserName("배준형"))
        ));

        List<ChattingRoomDto> chattingRoomDtos = chattingRoomService.list(userId);

        assertThat(chattingRoomDtos).hasSize(2);
    }

    @Test
    void fetchByTrainerId() {
        Long userId = 1L;

        given(chattingRoomRepository.findAllByUserId(userId)).willReturn(List.of(
                ChattingRoom.fake(new UserName("오진성")),
                ChattingRoom.fake(new UserName("배준형"))
        ));

        List<ChattingRoomDto> chattingRoomDtos = chattingRoomService.list(userId);

        assertThat(chattingRoomDtos).hasSize(2);
    }

    @Test
    void findById() {
        Long roomId = 1L;

        given(chattingRoomRepository.getReferenceById(roomId)).willReturn(
                ChattingRoom.fake(new UserName("오진성"))
        );

        ChattingRoomDto chattingRoomDto = chattingRoomService.findById(roomId);

        assertThat(chattingRoomDto.getUserName()).isEqualTo("오진성");
    }
}