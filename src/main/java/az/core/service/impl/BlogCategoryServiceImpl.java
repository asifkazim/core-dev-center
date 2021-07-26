package az.core.service.impl;

import az.core.mapper.BlogCategoryMapper;
import az.core.model.dto.BlogCategoryDto;
import az.core.model.entity.BlogCategory;
import az.core.repository.BlogCategoryRepository;
import az.core.service.BlogCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogCategoryServiceImpl implements BlogCategoryService {

    private final BlogCategoryRepository categoryRepository;
    private final BlogCategoryMapper blogCategoryMapper;

    @Override
    public List<BlogCategoryDto> getAllCategories() {
        List<BlogCategory> category = categoryRepository.findAll();
        return blogCategoryMapper.entitiesToDto(category);
    }

    @Override
    public BlogCategoryDto getById(Long id) {
        BlogCategory category = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return blogCategoryMapper.entityToDto(category);
    }

    @Override
    public void addCategory(String categoryName) {
        BlogCategory blogCategory;
        blogCategory = new BlogCategory();
        blogCategory.setName(categoryName);
        categoryRepository.save(blogCategory);
    }

    @Override
    public BlogCategoryDto updateCategory(Long id, BlogCategoryDto blogCategoryDto) {

        BlogCategory blogCategory;
        blogCategory = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (blogCategory != null) {
            blogCategory = blogCategoryMapper.dtoToEntity(blogCategoryDto);
        }
        categoryRepository.save(blogCategory);

        return blogCategoryDto;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
