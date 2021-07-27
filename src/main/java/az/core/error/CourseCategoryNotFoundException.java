package az.core.error;

public class CourseCategoryNotFoundException extends CommonException {

    public CourseCategoryNotFoundException(String code, String message) {
        super(code, message);
    }
}
