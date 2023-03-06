package megatera.makaoGymbackEnd.exceptions;

public class DeleteError extends RuntimeException {
    public DeleteError(String errorMessage) {
        super(errorMessage);
    }
}
