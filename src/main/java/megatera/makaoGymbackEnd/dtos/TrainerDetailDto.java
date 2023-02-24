package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class TrainerDetailDto {
    private TrainerResultDto trainerInformation;
    private boolean unCheckedChat;
    private boolean unCheckedRequest;

    public TrainerDetailDto() {
    }

    public TrainerDetailDto(TrainerResultDto trainerInformation) {
        this.trainerInformation = trainerInformation;
    }

    public void checkChat(boolean unCheckedChat) {
        this.unCheckedChat = unCheckedChat;
    }

    public void checkRequest(boolean unCheckedRequest) {
        this.unCheckedRequest = unCheckedRequest;
    }

    public TrainerResultDto getTrainerInformation() {
        return trainerInformation;
    }

    public boolean isUnCheckedChat() {
        return unCheckedChat;
    }

    public boolean isUnCheckedRequest() {
        return unCheckedRequest;
    }
}
