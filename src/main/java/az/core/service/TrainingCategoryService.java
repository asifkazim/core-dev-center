package az.core.service;

import az.core.model.dto.request.TrainingCategoryRequestDto;
import az.core.model.dto.response.TrainingCategoryResponseDto;

import java.util.List;

public interface TrainingCategoryService {

    List<TrainingCategoryResponseDto> getAllCategories();

    TrainingCategoryResponseDto getById(Long id);

    TrainingCategoryResponseDto updateCategory(Long id, TrainingCategoryRequestDto categoryRequestDto);

    TrainingCategoryResponseDto deleteCategory(Long id);

    TrainingCategoryResponseDto addCategory(TrainingCategoryRequestDto categoryRequestDto);
}
