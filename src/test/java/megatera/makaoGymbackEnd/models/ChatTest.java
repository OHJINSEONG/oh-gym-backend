package megatera.makaoGymbackEnd.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChatTest {
    @Test
    void check(){
        Chat chat = Chat.fake(new UserName("오진성"));

        chat.checked();

        assertThat(chat.status()).isEqualTo("CHECKED");
    }

    @Test
    void other(){
        Chat chat = Chat.fake(new UserName("오진성"));

        assertThat(chat.other("오진욱")).isTrue();
    }
}