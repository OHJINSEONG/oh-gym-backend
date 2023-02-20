package megatera.makaoGymbackEnd.dtos;

import megatera.makaoGymbackEnd.models.Status;

import java.time.LocalDateTime;

public class NotificationDto {
    private Long id;
    private Long userId;
    private String content;
    private String status;
    private String type;
    private String createTime;

    public NotificationDto() {
    }

    public NotificationDto(Long id, Long userId, String content, String status, String type, String createTime) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.status = status;
        this.type = type;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getCreateTime() {
        return createTime;
    }
}
