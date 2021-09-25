package az.core.service;

import az.core.model.dto.request.CourseCategoryRequestDto;
import az.core.model.dto.request.CourseCategoryRequestDto;
import az.core.model.dto.response.CourseCategoryResponseDto;
import az.core.model.dto.response.CourseCategoryResponseDto;

import java.util.List;

public interface CourseCategoryService {

    List<CourseCategoryResponseDto> getAllCategories();

    CourseCategoryResponseDto getById(Long id);

    CourseCategoryResponseDto updateCategory(Long id, CourseCategoryRequestDto categoryRequestDto);

    CourseCategoryResponseDto deleteCategory(Long id);

    CourseCategoryResponseDto addCategory(CourseCategoryRequestDto categoryRequestDto);
}
