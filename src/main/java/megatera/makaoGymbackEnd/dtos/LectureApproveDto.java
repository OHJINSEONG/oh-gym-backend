package megatera.makaoGymbackEnd.dtos;

public class LectureApproveDto {
    private Long userId;
    private String message;

    public LectureApproveDto() {
    }

    public LectureApproveDto(Long userId,String message) {
        this.userId = userId;
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }
}
