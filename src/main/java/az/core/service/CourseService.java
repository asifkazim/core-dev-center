package az.core.service;

import az.core.model.dto.request.CourseRequestDto;
import az.core.model.dto.response.CourseResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {

    List<CourseResponseDto> getAllCourse();

    CourseResponseDto getById(Long id);

    CourseResponseDto addCourse(CourseRequestDto courseRequestDto);

    CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto);

    CourseResponseDto deleteCourse(Long id);

    String uploadImage(MultipartFile file, Long id);

    String updateImage(MultipartFile file, Long id);

    void deleteUserImage(Long id);

    void deleteFile(String fileName, String folder);

    byte[] getFile(String fileName, String folder);
}
