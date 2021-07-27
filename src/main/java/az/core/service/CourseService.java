package az.core.service;

import az.core.model.dto.CourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> getAllCourses();

    CourseDto getById(Long id);

    CourseDto addCourse(CourseDto courseDto) throws Exception;

    CourseDto updateCourse(Long id, CourseDto courseDto);

    void deleteCourse(Long id);
}
