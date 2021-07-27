package az.core.error;

public class UserNotFoundException extends CommonException {

    public UserNotFoundException(String code, String message) {
        super(code, message);
    }
}
