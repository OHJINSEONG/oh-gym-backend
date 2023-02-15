package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.DiaryResultDto;
import megatera.makaoGymbackEnd.dtos.TrainerManagementDto;
import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.models.PtTicket;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminTrainerService {
    private final PtTicketService ptTicketService;
    private final UserService userService;
    private final DiaryService diaryService;

    public AdminTrainerService(PtTicketService ptTicketService, UserService userService, DiaryService diaryService) {
        this.ptTicketService = ptTicketService;
        this.userService = userService;
        this.diaryService = diaryService;
    }

    public List<TrainerManagementDto> management(Long trainerId) {
        List<TrainerManagementDto> trainerManagementDtos = new ArrayList<>();

        List<PtTicket> ptTickets = ptTicketService.findAllByTrainerId(trainerId);

        for (PtTicket ptTicket : ptTickets) {
            UserDto userDto = userService.find(ptTicket.userId());
            List<DiaryResultDto> diaryResultDtos = diaryService.list(ptTicket.userId());
            trainerManagementDtos.add(new TrainerManagementDto(ptTicket.toDto(), userDto,diaryResultDtos));
        }

        return trainerManagementDtos;
    }
}
