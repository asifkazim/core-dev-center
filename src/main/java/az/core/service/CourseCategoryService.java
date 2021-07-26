package az.core.service;

import az.core.model.dto.CourseCategoryDto;
import java.util.List;

public interface CourseCategoryService {

    List<CourseCategoryDto> getAllCategories();

    CourseCategoryDto getById(Long id);

    void addCategory(String categoryName);

    CourseCategoryDto updateCategory(Long id, CourseCategoryDto courseCategory);

    void deleteCategory(Long id);
}
