package az.core.controller;

import az.core.model.dto.request.CourseRequestDto;
import az.core.model.dto.response.CourseResponseDto;
import az.core.model.dto.FileDto;
import az.core.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Value("${minio.image-folder}")
    private String imageFolder;


    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        List<CourseResponseDto> courses = courseService.getAllCourse();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getById(@PathVariable Long id) {
        CourseResponseDto course = courseService.getById(id);
        return ResponseEntity.ok(course);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> addCourse(@RequestBody CourseRequestDto courseRequestDto) {
        CourseResponseDto course = courseService.addCourse(courseRequestDto);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto courseRequestDto) {
        CourseResponseDto course = courseService.updateCourse(id, courseRequestDto);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseResponseDto> deleteCourse(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.deleteCourse(id));
    }

    @PostMapping(value = "/image/{id}")
    public ResponseEntity<FileDto> createImage(@PathVariable Long id, @Valid @RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDto(courseService.uploadImage(file, id)));
    }

    @PutMapping(value = "/image/{id}")
    public ResponseEntity<FileDto> updateImage(@PathVariable Long id, @Valid @RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDto(courseService.updateImage(file, id)));
    }

    @GetMapping(value = "/image/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@PathVariable("fileName") String fileName) {
        return courseService.getFile(fileName, imageFolder);
    }

    @DeleteMapping("/image/{id}")
    public void deleteUserImage(@PathVariable("id") Long id) {
        courseService.deleteUserImage(id);
    }
}
