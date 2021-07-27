package az.core.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerBlogNotFoundException(BlogNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getCode(), ex.getMessage());
        log.error("BlogNotFoundException , error:{}, devMessage:{}", response, response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlogCategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerBlogCategoryNotFoundException(BlogCategoryNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getCode(), ex.getMessage());
        log.error("BlogCategoryNotFoundException , error:{}, devMessage:{}", response, response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerContactNotFoundException(ContactNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getCode(), ex.getMessage());
        log.error("ContactNotFoundException , error:{}, devMessage:{}", response, response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerCourseNotFoundException(CourseNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getCode(), ex.getMessage());
        log.error("CourseNotFoundException , error:{}, devMessage:{}", response, response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseCategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerCourseCategoryNotFoundException(CourseCategoryNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getCode(), ex.getMessage());
        log.error("CourseCategoryNotFoundException , error:{}, devMessage:{}", response, response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerEventNotFoundException(EventNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getCode(), ex.getMessage());
        log.error("EventNotFoundException , error:{}, devMessage:{}", response, response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getCode(), ex.getMessage());
        log.error("UserNotFoundException , error:{}, devMessage:{}", response, response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
