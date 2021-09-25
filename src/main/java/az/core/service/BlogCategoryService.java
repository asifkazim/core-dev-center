package az.core.service;

import az.core.model.dto.request.BlogCategoryRequestDto;
import az.core.model.dto.request.BlogRequestDto;
import az.core.model.dto.response.BlogCategoryResponseDto;
import az.core.model.dto.response.BlogResponseDto;

import java.util.List;

public interface BlogCategoryService {

    List<BlogCategoryResponseDto> getAllCategories();

    BlogCategoryResponseDto getById(Long id);

    BlogCategoryResponseDto updateCategory(Long id, BlogCategoryRequestDto categoryRequestDto);

    BlogCategoryResponseDto deleteCategory(Long id);

    BlogCategoryResponseDto addCategory(BlogCategoryRequestDto categoryRequestDto);
}
