package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.ChattingRoomDto;
import megatera.makaoGymbackEnd.dtos.ChattingRoomRegisterDto;
import megatera.makaoGymbackEnd.models.ChattingRoom;
import megatera.makaoGymbackEnd.services.ChattingRoomService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chattingRooms")
public class ChattingRoomController {
    private final ChattingRoomService chattingRoomService;

    public ChattingRoomController(ChattingRoomService chattingRoomService) {
        this.chattingRoomService = chattingRoomService;
    }

    @PostMapping
    public ChattingRoomDto create(
            @RequestBody ChattingRoomRegisterDto chattingRoomRegisterDto,
            @RequestAttribute("userId") Long userId
    ) {
        return chattingRoomService.create(chattingRoomRegisterDto.getTrainerId(), userId);
    }
}
