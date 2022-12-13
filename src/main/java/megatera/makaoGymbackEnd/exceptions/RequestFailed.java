package megatera.makaoGymbackEnd.exceptions;

public class RequestFailed extends RuntimeException {
    private Integer code;

    public RequestFailed(String context) {
        super("이미 예약된 시간입니다.");
        this.code = 1000;
    }

    public Integer getCode() {
        return code;
    }
}
