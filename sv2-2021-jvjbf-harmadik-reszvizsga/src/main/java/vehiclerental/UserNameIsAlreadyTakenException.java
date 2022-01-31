package vehiclerental;

public class UserNameIsAlreadyTakenException extends RuntimeException {

    public UserNameIsAlreadyTakenException() {
    }

    public UserNameIsAlreadyTakenException(String message) {
        super(message);
    }

    public UserNameIsAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
