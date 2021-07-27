package az.core.error;

public class CourseNotFoundException extends CommonException {

    public CourseNotFoundException(String code, String message) {
        super(code, message);
    }
}
