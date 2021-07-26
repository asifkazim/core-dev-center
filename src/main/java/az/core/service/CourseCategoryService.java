package az.core.service;

import az.core.model.entity.CourseCategory;

import java.util.List;

public interface CourseCategoryService {
    List<CourseCategory> getAllCategories();

    CourseCategory getById(Long id);

    CourseCategory addCategory(CourseCategory courseCategory);

    CourseCategory updateCategory(CourseCategory courseCategory);

    void deleteCategory(Long id);
}
