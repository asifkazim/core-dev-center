package az.core.service.impl;

import az.core.error.EntityNotFoundException;
import az.core.mapper.BlogCategoryMapper;
import az.core.model.dto.request.BlogCategoryRequestDto;
import az.core.model.dto.response.BlogCategoryResponseDto;
import az.core.model.dto.response.BlogResponseDto;
import az.core.model.entity.Blog;
import az.core.model.entity.BlogCategory;
import az.core.repository.BlogCategoryRepository;
import az.core.service.BlogCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogCategoryServiceImpl implements BlogCategoryService {

    private final BlogCategoryRepository categoryRepository;
    private final BlogCategoryMapper blogCategoryMapper;

    @Override
    public List<BlogCategoryResponseDto> getAllCategories() {
        log.info("getAllBlogCategory requesting...");
        List<BlogCategory> category = categoryRepository.findAll();
        log.info("getAllBlogCategory Response started with: {}", kv("category", category));
        return blogCategoryMapper.entitiesToDto(category);
    }

    @Override
    public BlogCategoryResponseDto getById(Long id) {
        log.info("getById BlogCategory started with: {}", kv("id", id));
        BlogCategory category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(BlogCategory.class, id);
        });
        BlogCategoryResponseDto responseDto = blogCategoryMapper.entityToDto(category);
        log.info("getById BlogCategory completed successfully with: {}", kv("id", id));
        return responseDto;

    }

    @Override
    public BlogCategoryResponseDto addCategory(BlogCategoryRequestDto categoryRequestDto) {
        log.info("create BlogCategory started with:{}", categoryRequestDto);
        BlogCategory category = blogCategoryMapper.dtoToEntity(categoryRequestDto);
        categoryRepository.save(category);
        BlogCategoryResponseDto categoryResponseDto = blogCategoryMapper.entityToDto(category);
        log.info("create BlogCategory completed successfully with: {}", kv("category", categoryResponseDto));
        return categoryResponseDto;
    }

    @Override
    public BlogCategoryResponseDto updateCategory(Long id, BlogCategoryRequestDto categoryRequestDto) {
        log.info("update BlogCategory started with: {}, {}", kv("id", id),
                kv("blogCategoryRequestDto", categoryRequestDto));
        BlogCategory category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(BlogCategory.class, id);
        });
        if (category != null) {
            category = blogCategoryMapper.dtoToEntity(categoryRequestDto);
            category.setId(id);
        }
        categoryRepository.save(category);
        BlogCategoryResponseDto responseDto = blogCategoryMapper.entityToDto(category);
        log.info("update BlogCategory completed successfully with: {}, {}", kv("id", id),
                kv("category", responseDto));
        return responseDto;
    }

    @Override
    public BlogCategoryResponseDto deleteCategory(Long id) {
        log.info("Delete BlogCategory started with: {}", kv("id", id));
        BlogCategory category = categoryRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(BlogCategory.class, id);
                }
        );
        categoryRepository.delete(category);
        BlogCategoryResponseDto responseDto = blogCategoryMapper.entityToDto(category);
        log.info("delete BlogCategory completed successfully with: {}", kv("id", id));
        return responseDto;
    }
}
