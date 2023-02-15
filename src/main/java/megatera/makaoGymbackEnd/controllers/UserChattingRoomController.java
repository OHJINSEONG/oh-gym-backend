package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.ChattingRoomDetailDto;
import megatera.makaoGymbackEnd.dtos.ChattingRoomDto;
import megatera.makaoGymbackEnd.dtos.ChattingRoomRegisterDto;
import megatera.makaoGymbackEnd.services.ChattingRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/chattingRooms")
public class UserChattingRoomController {
    private final ChattingRoomService chattingRoomService;

    public UserChattingRoomController(ChattingRoomService chattingRoomService) {
        this.chattingRoomService = chattingRoomService;
    }

    @GetMapping
    public List<ChattingRoomDto> list(
            @RequestAttribute("userId") Long userId
    ) {
        return chattingRoomService.list(userId);
    }
}
