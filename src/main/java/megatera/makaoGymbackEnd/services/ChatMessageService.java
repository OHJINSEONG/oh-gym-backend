package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.ChatMessageDto;
import megatera.makaoGymbackEnd.models.Chat;
import megatera.makaoGymbackEnd.models.Message;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.repositories.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ChatMessageService {
    private final ChatRepository chatRepository;

    public ChatMessageService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public ChatMessageDto save(String message, Long roomId, String writer) {
        Chat chat = new Chat(roomId, new UserName(writer), new Message(message));

        chat.created();

        chat.setTime(LocalDateTime.now());

        chatRepository.save(chat);

        return chat.toDto();
    }

    public List<ChatMessageDto> fetchChats(Long roomId) {
        List<Chat> chats = chatRepository.findAllByRoomId(roomId);

        chats.forEach(Chat::checked);

        return chats.stream().map(Chat::toDto).toList();
    }

    public Long countUnChecked(Long roomId, String userName) {
        return (long) chatRepository.findAllByRoomId(roomId).stream().filter(chat -> chat.other(userName)
                && !chat.status().equals("CHECKED")).toList().size();
    }
}
