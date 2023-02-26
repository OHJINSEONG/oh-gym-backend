package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.ChatMessageDto;
import megatera.makaoGymbackEnd.models.Chat;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.repositories.ChatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ChatMessageServiceTest {
    private ChatMessageService chatMessageService;

    private ChatRepository chatRepository;

    @BeforeEach
    void setup() {
        chatRepository = mock(ChatRepository.class);
        chatMessageService = new ChatMessageService(chatRepository);
    }

    @Test
    void save() {
        String message = "안녕";
        Long roomId = 1L;
        String writer = "오진성";
        Long userId = 1L;

        ChatMessageDto chatMessageDto = chatMessageService.save(message, roomId, writer, userId);

        assertThat(chatMessageDto.getMessage()).isEqualTo("안녕");
    }

    @Test
    void fetchChats() {
        given(chatRepository.findAllByRoomId(any())).willReturn(List.of(
                Chat.fake(new UserName("오진성")),
                Chat.fake(new UserName("오진욱")),
                Chat.fake(new UserName("오진성"))
        ));

        List<ChatMessageDto> chatMessageDto = chatMessageService.fetchChats(any());

        assertThat(chatMessageDto).hasSize(3);
    }

    @Test
    void countUnChecked() {
        Long roomId = 1L;
        Long userId = 2L;

        given(chatRepository.findAllByRoomId(any())).willReturn(List.of(
                Chat.fake(new UserName("오진성")),
                Chat.fake(new UserName("오진욱")),
                Chat.fake(new UserName("오진욱"))
        ));
        Long countUnChecked = chatMessageService.countUnChecked(roomId, userId);

        assertThat(countUnChecked).isEqualTo(3L);
    }
}