package az.core.service.impl;

import az.core.error.BlogCategoryNotFoundException;
import az.core.mapper.BlogCategoryMapper;
import az.core.model.dto.BlogCategoryDto;
import az.core.model.entity.BlogCategory;
import az.core.repository.BlogCategoryRepository;
import az.core.service.BlogCategoryService;
import az.core.util.ApplicationCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogCategoryServiceImpl implements BlogCategoryService {

    private final BlogCategoryRepository categoryRepository;
    private final BlogCategoryMapper blogCategoryMapper;

    @Override
    public List<BlogCategoryDto> getAllCategories() {
        log.info("Request, GET All Blog Categories");
        List<BlogCategory> categories = categoryRepository.findAll();
        log.info("Response, blog categories:{}", categories);
        return blogCategoryMapper.entitiesToDto(categories);
    }

    @Override
    public BlogCategoryDto getById(Long id) {
        log.info("Request, GET Blog Category By Id, id:{}", id);
        BlogCategory category = categoryRepository.findById(id).orElseThrow(
                () -> new BlogCategoryNotFoundException(ApplicationCode.BLOG_CATEGORY_NOT_FOUND, "Blog Category not found!")
        );
        log.info("Response, Blog Category By Id, id:{}, blog category:{} ", id, category);
        return blogCategoryMapper.entityToDto(category);
    }

    @Override
    public void addCategory(String categoryName) {
        log.info("Request, ADD Blog Category, category name:{}", categoryName);
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setName(categoryName);
        categoryRepository.save(blogCategory);
        log.info("Response, ADDED Blog Category");

    }

    @Override
    public BlogCategoryDto updateCategory(Long id, BlogCategoryDto blogCategoryDto) {
        log.info("Request, UPDATE Blog Category, id:{}, blog category:{}", id, blogCategoryDto);
        BlogCategory blogCategory = categoryRepository.findById(id).orElseThrow(
                () -> new BlogCategoryNotFoundException(ApplicationCode.BLOG_CATEGORY_NOT_FOUND, "Blog Category not found!")
        );

        if (blogCategory != null) {
            blogCategory = blogCategoryMapper.dtoToEntity(blogCategoryDto);
        }
        categoryRepository.save(blogCategory);
        log.info("Response, UPDATED Blog Category, id:{}, blog category:{}", id, blogCategoryDto);
        return blogCategoryDto;
    }

    @Override
    public void deleteCategory(Long id) {
        log.info("Request, DELETE Blog Category, id:{}", id);
        categoryRepository.deleteById(id);
        log.info("Response, DELETED Blog Category");
    }
}
