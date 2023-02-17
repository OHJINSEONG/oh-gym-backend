package megatera.makaoGymbackEnd.exceptions;

public class InValidEmail extends RuntimeException {
    public InValidEmail(){
        super("카카오 이메일 동의를 해주세요.");
    }
}
