package az.core.service.impl;

import az.core.model.entity.CourseCategory;
import az.core.repository.CourseCategoryRepository;
import az.core.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryRepository categoryRepository;


    @Override
    public List<CourseCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CourseCategory getById(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public CourseCategory addCategory(CourseCategory courseCategory) {
        return categoryRepository.save(courseCategory);
    }

    @Override
    public CourseCategory updateCategory(CourseCategory courseCategory) {
        CourseCategory existCategory = categoryRepository.getOne(courseCategory.getId());
        existCategory.setName(courseCategory.getName());
        return categoryRepository.save(existCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
