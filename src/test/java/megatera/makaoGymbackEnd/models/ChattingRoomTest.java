package megatera.makaoGymbackEnd.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChattingRoomTest {
    @Test
    void setTime(){
        ChattingRoom chattingRoom = ChattingRoom.fake(new UserName("오진성"));

        chattingRoom.updateTime(LocalDateTime.parse("2022-12-10T11:00"));

        assertThat(chattingRoom.updateAt().toString()).isEqualTo("2022-12-10T11:00");
    }

    @Test
    void setMessage(){
        ChattingRoom chattingRoom = ChattingRoom.fake(new UserName("오진성"));

        chattingRoom.setMessage("하이입니다");

        assertThat(chattingRoom.message()).isEqualTo("하이입니다");
    }
}