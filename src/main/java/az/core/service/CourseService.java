package az.core.service;

import az.core.model.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();

    Course getById(Long id);

    Course addCourse(Course course);

    Course updateCourse(Long id,Course course);

    void deleteCourse(Long id);
}
