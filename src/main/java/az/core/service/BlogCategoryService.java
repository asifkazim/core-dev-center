package az.core.service;

import az.core.model.dto.BlogCategoryDto;

import java.util.List;

public interface BlogCategoryService {

    List<BlogCategoryDto> getAllCategories();

    BlogCategoryDto getById(Long id);

    BlogCategoryDto updateCategory(Long id, BlogCategoryDto blogCategoryDto);

    void deleteCategory(Long id);

    void addCategory(String categoryName);
}
