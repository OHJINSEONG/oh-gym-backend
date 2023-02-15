package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.*;
import megatera.makaoGymbackEnd.services.ChatMessageService;
import megatera.makaoGymbackEnd.services.ChattingRoomService;
import megatera.makaoGymbackEnd.utils.JwtUtil;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class MessageController {
    private final SimpMessagingTemplate template;
    private final ChatMessageService chatMessageService;
    private final ChattingRoomService chattingRoomService;
    private final JwtUtil jwtUtil;

    public MessageController(SimpMessagingTemplate template, ChatMessageService chatMessageService, ChattingRoomService chattingRoomService, JwtUtil jwtUtil) {
        this.template = template;
        this.chatMessageService = chatMessageService;
        this.chattingRoomService = chattingRoomService;
        this.jwtUtil = jwtUtil;
    }

    @MessageMapping("/user/chat/enter")
    public void userEnter(
            ChattingRoomFindDto chattingRoomFindDto
    ) {
        ChattingRoomDto chattingRoomDto = chattingRoomService.findById(chattingRoomFindDto.getRoomId());

        ChattingEnterDto chattingEnterDto = new ChattingEnterDto(chattingRoomDto.getUserName(), chattingRoomDto.getTrainerName(), chattingRoomDto.getTrainerImage());

        template.convertAndSend("/sub/user/chat", chattingEnterDto);
    }

    @MessageMapping("/user/chatList/enter")
    public void userChattingListEnter(
            SimpMessageHeaderAccessor headerAccessor
    ) {
        String headers = headerAccessor.getMessageHeaders().get("nativeHeaders").toString();

        String accessToken = headers.substring(
                "Authorization=[Bearer  ".length(), headers.indexOf("]"));

        Long userId = jwtUtil.decode(accessToken);

        List<ChattingRoomDto> chattingRoomDtos = chattingRoomService.list(userId);

        List<ChattingRoomDetailDto> chattingRoomDetailDtos = new ArrayList<>();

        for (ChattingRoomDto chattingRoomDto : chattingRoomDtos) {
            Long count = chatMessageService.countUnChecked(chattingRoomDto.getId(), chattingRoomDto.getUserName());
            chattingRoomDetailDtos.add(new ChattingRoomDetailDto(chattingRoomDto, count));
        }

        template.convertAndSend("/sub/user/chattingRooms", chattingRoomDetailDtos);
    }

    @MessageMapping("/trainer/chat/enter")
    public void trainerEnter(
            TrainerChattingRoomFindDto trainerChattingRoomFindDto
    ) {
        Long trainerId = trainerChattingRoomFindDto.getTrainerId();

        List<ChattingRoomDto> chattingRoomDtos = chattingRoomService.fetchByTrainerId(trainerId);

        template.convertAndSend("/sub/trainer/" + trainerId + "/chattingRooms", chattingRoomDtos);
    }

    @MessageMapping("/chat/messages")
    public void messageList(
            ChattingRoomFindDto chattingRoomFindDto
    ) {
        Long roomId = chattingRoomFindDto.getRoomId();

        List<ChatMessageDto> chatMessageDtos = chatMessageService.fetchChats(roomId);

        template.convertAndSend("/sub/chats/room/" + roomId, chatMessageDtos);
    }

    @MessageMapping("/chat/message")
    public void message(
            ChatMessageRegisterDto chatMessageRegisterDto
    ) {
        ChatMessageDto chatMessageDto = chatMessageService.save(
                chatMessageRegisterDto.getMessage(),
                chatMessageRegisterDto.getRoomId(),
                chatMessageRegisterDto.getWriter()
        );

        chattingRoomService.update(chatMessageRegisterDto.getRoomId(), chatMessageRegisterDto.getMessage());

        template.convertAndSend("/sub/chat/room/" + chatMessageDto.getRoomId(), chatMessageDto);
    }

    @MessageMapping("/chat/transfer")
    public void transfer(
            ChatMessageRegisterDto chatMessageRegisterDto
    ) {
        ChattingRoomDto chattingRoomDto = chattingRoomService.findById(chatMessageRegisterDto.getRoomId());

        template.convertAndSend("/sub/user/" + chattingRoomDto.getUserEmail(), "ㅎㅇ");
    }
}
