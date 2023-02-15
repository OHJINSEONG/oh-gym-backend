package megatera.makaoGymbackEnd.dtos;

import megatera.makaoGymbackEnd.models.PtTicket;

import java.util.List;

public class TrainerManagementDto {
    private PtTicketDto ptTicket;
    private UserDto user;
    private List<DiaryResultDto> diarys;

    public TrainerManagementDto() {
    }

    public TrainerManagementDto(PtTicketDto ptTicket, UserDto user,List<DiaryResultDto> diarys) {
        this.ptTicket = ptTicket;
        this.user = user;
        this.diarys = diarys;
    }

    public PtTicketDto getPtTicket() {
        return ptTicket;
    }

    public UserDto getUser() {
        return user;
    }

    public List<DiaryResultDto> getDiarys() {
        return diarys;
    }
}
