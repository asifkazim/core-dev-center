package az.core.error;

public class ContactNotFoundException extends CommonException {

    public ContactNotFoundException(String code, String message) {
        super(code, message);
    }
}
