package az.core.error;

public class BlogCategoryNotFoundException extends CommonException {

    public BlogCategoryNotFoundException(String code, String message) {
        super(code, message);
    }
}
