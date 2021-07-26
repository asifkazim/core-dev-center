package az.core.service.impl;

import az.core.mapper.CourseMapper;
import az.core.model.dto.CourseDto;
import az.core.model.entity.Course;
import az.core.model.entity.CourseCategory;
import az.core.repository.CourseCategoryRepository;
import az.core.repository.CourseRepository;
import az.core.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseCategoryRepository courseCategoryRepository;


    @Override
    public List<CourseDto> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.entitiesToDto(courses);

    }

    @Override
    public CourseDto getById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        return courseMapper.entityToDto(course);
    }

    @Override
    public CourseDto addCourse(CourseDto courseDto) throws Exception {
        CourseCategory category = courseCategoryRepository.findByName(courseDto.getCourseCategory());
        if (category != null) {

            Course course = courseMapper.dtoToEntity(courseDto);
            course.setCourseCategory(category);
            courseRepository.save(course);
            return courseDto;
        } else {
            throw new Exception();
        }
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        Course course = courseRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (course != null) {
            course = courseMapper.dtoToEntity(courseDto);
            course.setId(id);

            CourseCategory category = courseCategoryRepository.findByName(courseDto.getCourseCategory());
            course.setCourseCategory(category);
        }
        courseRepository.save(course);
        return courseDto;

    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
