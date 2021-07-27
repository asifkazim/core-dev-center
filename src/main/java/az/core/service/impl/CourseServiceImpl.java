package az.core.service.impl;

import az.core.error.CourseCategoryNotFoundException;
import az.core.error.CourseNotFoundException;
import az.core.mapper.CourseMapper;
import az.core.model.dto.CourseDto;
import az.core.model.entity.Course;
import az.core.model.entity.CourseCategory;
import az.core.repository.CourseCategoryRepository;
import az.core.repository.CourseRepository;
import az.core.service.CourseService;
import az.core.util.ApplicationCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseCategoryRepository courseCategoryRepository;


    @Override
    public List<CourseDto> getAllCourses() {
        log.info("Request, GET All Courses");
        List<Course> courses = courseRepository.findAll();
        log.info("Response, courses:{}", courses);
        return courseMapper.entitiesToDto(courses);
    }

    @Override
    public CourseDto getById(Long id) {
        log.info("Request, GET Course By Id, id:{}", id);
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new CourseNotFoundException(ApplicationCode.COURSE_NOT_FOUND, "Course not found!")
        );
        log.info("Response, Course By Id, id:{}, course:{}", id, course);
        return courseMapper.entityToDto(course);
    }

    @Override
    public CourseDto addCourse(CourseDto courseDto) throws Exception {
        log.info("Request, ADD Course, blog:{}", courseDto);
        log.info("Request, GET Course Category By Name, name:{}", courseDto.getCourseCategory());
        CourseCategory category = courseCategoryRepository.findByName(courseDto.getCourseCategory()).orElseThrow(
                () -> new CourseCategoryNotFoundException(ApplicationCode.COURSE_CATEGORY_NOT_FOUND, "Course Category not found!")
        );
        if (category != null) {

            Course course = courseMapper.dtoToEntity(courseDto);
            course.setCourseCategory(category);
            courseRepository.save(course);
            log.info("Response, ADDED Course, course:{}", course);
            return courseDto;
        } else {
            throw new Exception();
        }
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        log.info("Request, UPDATE Course, id:{}, course:{}", id, courseDto);
        log.info("Request, GET Course By Id, id:{}", id);
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new CourseNotFoundException(ApplicationCode.COURSE_NOT_FOUND, "Course not found!")
        );

        if (course != null) {
            course = courseMapper.dtoToEntity(courseDto);
            course.setId(id);

            CourseCategory category = courseCategoryRepository.findByName(courseDto.getCourseCategory()).orElseThrow(
                    () -> new CourseCategoryNotFoundException(ApplicationCode.COURSE_CATEGORY_NOT_FOUND, "Course Category not found!")
            );
            course.setCourseCategory(category);
        }
        courseRepository.save(course);
        log.info("Response, UPDATED Course, id:{}, course:{}", id, course);
        return courseDto;

    }

    @Override
    public void deleteCourse(Long id) {
        log.info("Request, DELETE Course, id:{}", id);
        courseRepository.deleteById(id);
        log.info("Response, DELETED Course");
    }
}
