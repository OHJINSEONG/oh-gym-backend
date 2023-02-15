package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.ChattingRoomDetailDto;
import megatera.makaoGymbackEnd.dtos.ChattingRoomDto;
import megatera.makaoGymbackEnd.services.ChattingRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerChattingRoomController {
    private final ChattingRoomService chattingRoomService;

    public TrainerChattingRoomController(ChattingRoomService chattingRoomService) {
        this.chattingRoomService = chattingRoomService;
    }

    @GetMapping("{trainerId}/chattingRooms")
    public List<ChattingRoomDto> fetchByTrainerId(
            @PathVariable Long trainerId
    ) {
        return chattingRoomService.fetchByTrainerId(trainerId);
    }
}
