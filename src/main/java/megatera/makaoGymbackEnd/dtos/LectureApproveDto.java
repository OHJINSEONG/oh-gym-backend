package megatera.makaoGymbackEnd.dtos;

public class LectureApproveDto {
    private Long userId;

    public LectureApproveDto() {
    }

    public LectureApproveDto(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
