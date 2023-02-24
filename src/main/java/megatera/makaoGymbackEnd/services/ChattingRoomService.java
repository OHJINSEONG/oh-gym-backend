package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.ChattingRoomDto;
import megatera.makaoGymbackEnd.dtos.TrainerResultDto;
import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.models.ChattingRoom;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.repositories.ChattingRoomRepository;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import megatera.makaoGymbackEnd.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChattingRoomService {
    private final ChattingRoomRepository chattingRoomRepository;
    private final ChatMessageService chatMessageService;
    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;

    public ChattingRoomService(ChattingRoomRepository chattingRoomRepository, ChatMessageService chatMessageService, UserRepository userRepository, TrainerRepository trainerRepository) {
        this.chattingRoomRepository = chattingRoomRepository;
        this.chatMessageService = chatMessageService;
        this.userRepository = userRepository;
        this.trainerRepository = trainerRepository;
    }

    public Optional<ChattingRoom> find(Long trainerId, Long userId) {
        return chattingRoomRepository.findByTrainerIdAndUserId(trainerId, userId);
    }

    public ChattingRoomDto create(Long trainerId, Long userId) {
        if (find(trainerId, userId).isPresent()) {
            return find(trainerId, userId).get().toDto();
        }

        UserDto userDto = userRepository.getReferenceById(userId).toDto();

        TrainerResultDto trainerResultDto = trainerRepository.getReferenceById(trainerId).toDto();

        ChattingRoom chattingRoom = new ChattingRoom(
                userId,
                trainerId,
                new UserName(userDto.getUserName()),
                new UserName(trainerResultDto.getUserName()),
                trainerResultDto.getImage(),
                userDto.getEmail()
        );

        chattingRoom.created();

        chattingRoomRepository.save(chattingRoom);

        return chattingRoom.toDto();
    }

    public List<ChattingRoomDto> list(Long userId) {
        return chattingRoomRepository.findAllByUserId(userId).stream()
                .map(ChattingRoom::toDto)
                .sorted(Comparator.comparing(ChattingRoomDto::getUpdateTime).reversed())
                .toList();
    }

    public List<ChattingRoomDto> fetchByTrainerId(Long trainerId) {
        return chattingRoomRepository.findAllByTrainerId(trainerId).stream()
                .map(ChattingRoom::toDto)
                .sorted(Comparator.comparing(ChattingRoomDto::getUpdateTime).reversed())
                .toList();
    }

    public void update(Long roomId, String message) {
        ChattingRoom chattingRoom = chattingRoomRepository.getReferenceById(roomId);

        chattingRoom.updateTime(LocalDateTime.now());

        chattingRoom.setMessage(message);
    }

    public ChattingRoomDto findById(Long roomId) {
        return chattingRoomRepository.getReferenceById(roomId).toDto();
    }

    public boolean findUnCheckedWithTrainerId(Long trainerId) {
        List<ChattingRoom> chattingRooms = chattingRoomRepository.findAllByTrainerId(trainerId);
        for (ChattingRoom chattingRoom : chattingRooms) {
            if (chatMessageService.countUnChecked(chattingRoom.id(), trainerId) != 0L) {
                return true;
            }
        }
        return false;
    }
}
