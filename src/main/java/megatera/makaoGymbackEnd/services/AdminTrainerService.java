package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.*;
import megatera.makaoGymbackEnd.models.*;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminTrainerService {
    private final PtTicketService ptTicketService;
    private final TrainerRepository trainerRepository;
    private final UserService userService;
    private final DiaryService diaryService;
    private final ChattingRoomService chattingRoomService;
    private final RequestService requestService;

    public AdminTrainerService(
            PtTicketService ptTicketService,
            TrainerRepository trainerRepository,
            UserService userService,
            DiaryService diaryService,
            ChattingRoomService chattingRoomService,
            RequestService requestService) {
        this.ptTicketService = ptTicketService;
        this.trainerRepository = trainerRepository;
        this.userService = userService;
        this.diaryService = diaryService;
        this.chattingRoomService = chattingRoomService;
        this.requestService = requestService;
    }

    public List<TrainerManagementDto> management(Long trainerId) {
        List<TrainerManagementDto> trainerManagementDtos = new ArrayList<>();

        List<PtTicket> ptTickets = ptTicketService.findAllByTrainerId(trainerId);

        for (PtTicket ptTicket : ptTickets) {
            UserDto userDto = userService.find(ptTicket.userId());
            List<DiaryResultDto> diaryResultDtos = diaryService.list(ptTicket.userId());
            trainerManagementDtos.add(new TrainerManagementDto(ptTicket.toDto(), userDto, diaryResultDtos));
        }

        return trainerManagementDtos;
    }

    public TrainerResultDto create(String name, String userName, String image) {
        Trainer trainer = new Trainer(
                new UserName(name),
                new Name(userName),
                new PhoneNumber("01085568965"),
                new Age("971117-1932201"),
                new Gender("남자"),
                image,
                LocalTime.parse("09:00"),
                LocalTime.parse("18:00")
        );

        trainer.toCreated();

        trainerRepository.save(trainer);

        return trainer.toDto();
    }

    public List<TrainerDetailDto> list() {
        List<Trainer> trainers = trainerRepository.findAll();

        List<TrainerDetailDto> trainerDetailDtos = new ArrayList<>();

        for (Trainer trainer : trainers) {
            TrainerDetailDto trainerDetailDto = new TrainerDetailDto(trainer.toDto());
            trainerDetailDto.checkChat(chattingRoomService.findUnCheckedWithTrainerId(trainer.id()));
            trainerDetailDto.checkRequest(requestService.checkFindAllByTrainerId(trainer.id()));

            trainerDetailDtos.add(trainerDetailDto);
        }

        return trainerDetailDtos;
    }
}
