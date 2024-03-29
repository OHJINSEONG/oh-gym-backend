package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.ChatMessageDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Chat {
    @Id
    @GeneratedValue
    private Long id;

    private Long roomId;

    private Long writerId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "writer"))
    private UserName writer;

    private Message message;

    private Status status;

    private LocalDateTime time;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Chat() {
    }

    public Chat(Long roomId, Long writerId, UserName writer, Message message) {
        this.roomId = roomId;
        this.writerId = writerId;
        this.writer = writer;
        this.message = message;
    }

    public static Chat fake(UserName username) {
        Chat chat = new Chat(1L, 1L, username, new Message("하이입니다."));

        chat.created();

        return chat;
    }

    public ChatMessageDto toDto() {
        return new ChatMessageDto(id, roomId, writer.value(), message.value(), status.value(), time);
    }

    public void created() {
        this.status = new Status("CREATED");
    }

    public void setTime(LocalDateTime now) {
        this.time = now;
    }

    public void checked() {
        this.status.toChecked();
    }

    public String status() {
        return this.status.value();
    }

    public boolean other(Long writerId) {
        return !Objects.equals(this.writerId, writerId);
    }
}
