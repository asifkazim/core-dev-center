package az.core.service.impl;

import az.core.model.entity.Course;
import az.core.repository.CourseRepository;
import az.core.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id , Course course) {
        Course existCourse = courseRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        existCourse.setName(course.getName());
        existCourse.setCost(course.getCost());
        existCourse.setTime(course.getTime());
        existCourse.setCourseCategory(course.getCourseCategory());
        existCourse.setDescription(course.getDescription());
        existCourse.setCourseMethod(course.getCourseMethod());
        existCourse.setCourseCategory(course.getCourseCategory());
        existCourse.setActive(course.getActive());
        existCourse.setImage(course.getImage());
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
