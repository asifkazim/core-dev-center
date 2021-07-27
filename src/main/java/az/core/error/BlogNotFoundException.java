package az.core.error;

public class BlogNotFoundException extends CommonException {

    public BlogNotFoundException(String code, String message) {
        super(code, message);
    }
}
