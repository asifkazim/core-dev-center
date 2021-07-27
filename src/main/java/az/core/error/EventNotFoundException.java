package az.core.error;

public class EventNotFoundException extends CommonException {

    public EventNotFoundException(String code, String message) {
        super(code, message);
    }
}
