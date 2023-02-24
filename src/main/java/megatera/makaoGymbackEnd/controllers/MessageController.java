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
            Long count = chatMessageService.countUnChecked(chattingRoomDto.getId(), userId);
            chattingRoomDetailDtos.add(new ChattingRoomDetailDto(chattingRoomDto, count));
        }

        template.convertAndSend("/sub/user/chattingRooms", chattingRoomDetailDtos);
    }

    @MessageMapping("/trainer/chatList/enter")
    public void trainerEnter(
            TrainerChattingRoomFindDto trainerChattingRoomFindDto
    ) {
        Long trainerId = trainerChattingRoomFindDto.getTrainerId();

        List<ChattingRoomDto> chattingRoomDtos = chattingRoomService.fetchByTrainerId(trainerId);

        List<ChattingRoomDetailDto> chattingRoomDetailDtos = new ArrayList<>();

        for (ChattingRoomDto chattingRoomDto : chattingRoomDtos) {
            Long count = chatMessageService.countUnChecked(chattingRoomDto.getId(), trainerId);
            chattingRoomDetailDtos.add(new ChattingRoomDetailDto(chattingRoomDto, count));
        }

        template.convertAndSend("/sub/trainer/" + trainerId + "/chattingRooms", chattingRoomDetailDtos);
    }

    @MessageMapping("/user/chat/messages")
    public void messageList(
            ChattingRoomFindDto chattingRoomFindDto,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        Long roomId = chattingRoomFindDto.getRoomId();

        String headers = headerAccessor.getMessageHeaders().get("nativeHeaders").toString();

        String accessToken = headers.substring(
                "Authorization=[Bearer  ".length(), headers.indexOf("]"));

        Long userId = jwtUtil.decode(accessToken);

        List<ChatMessageDto> chatMessageDtos = chatMessageService.fetchTrainerChats(roomId, userId);

        template.convertAndSend("/sub/chats/room/" + roomId, chatMessageDtos);
    }

    @MessageMapping("/trainer/chat/messages")
    public void messageList(
            ChattingRoomFindDto chattingRoomFindDto
    ) {
        Long roomId = chattingRoomFindDto.getRoomId();

        Long trainerId = chattingRoomFindDto.getTrainerId();

        List<ChatMessageDto> chatMessageDtos = chatMessageService.fetchTrainerChats(roomId, trainerId);

        template.convertAndSend("/sub/chats/room/" + roomId, chatMessageDtos);
    }

    @MessageMapping("/user/chat/message")
    public void message(
            ChatMessageRegisterDto chatMessageRegisterDto,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        String headers = headerAccessor.getMessageHeaders().get("nativeHeaders").toString();

        String accessToken = headers.substring(
                "Authorization=[Bearer  ".length(), headers.indexOf("]"));

        Long userId = jwtUtil.decode(accessToken);

        ChatMessageDto chatMessageDto = chatMessageService.save(
                chatMessageRegisterDto.getMessage(),
                chatMessageRegisterDto.getRoomId(),
                chatMessageRegisterDto.getWriter(),
                userId
        );

        chattingRoomService.update(chatMessageRegisterDto.getRoomId(), chatMessageRegisterDto.getMessage());

        template.convertAndSend("/sub/chat/room/" + chatMessageDto.getRoomId(), chatMessageDto);
    }

    @MessageMapping("/trainer/chat/message")
    public void message(
            ChatMessageRegisterDto chatMessageRegisterDto
    ) {
        ChatMessageDto chatMessageDto = chatMessageService.save(
                chatMessageRegisterDto.getMessage(),
                chatMessageRegisterDto.getRoomId(),
                chatMessageRegisterDto.getWriter(),
                chatMessageRegisterDto.getTrainerId()
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
        template.convertAndSend("/sub/trainer/" + chattingRoomDto.getTrainerId(), "ㅎㅇ");
    }
}
