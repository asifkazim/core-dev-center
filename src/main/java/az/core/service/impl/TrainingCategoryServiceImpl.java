package az.core.service.impl;

import az.core.error.EntityNotFoundException;
import az.core.mapper.TrainingCategoryMapper;
import az.core.model.dto.request.TrainingCategoryRequestDto;
import az.core.model.dto.response.TrainingCategoryResponseDto;
import az.core.model.entity.TrainingCategory;
import az.core.repository.TrainingCategoryRepository;
import az.core.service.TrainingCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingCategoryServiceImpl implements TrainingCategoryService {

    private final TrainingCategoryRepository categoryRepository;
    private final TrainingCategoryMapper trainingCategoryMapper;

    @Override
    public List<TrainingCategoryResponseDto> getAllCategories() {
        log.info("getAllTrainingCategory requesting...");
        List<TrainingCategory> category = categoryRepository.findAll();
        log.info("getAllTrainingCategory Response started with: {}", kv("category", category));
        return trainingCategoryMapper.entitiesToDto(category);
    }

    @Override
    public TrainingCategoryResponseDto getById(Long id) {
        log.info("getById TrainingCategory started with: {}", kv("id", id));
        TrainingCategory category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(TrainingCategory.class, id);
        });
        TrainingCategoryResponseDto responseDto = trainingCategoryMapper.entityToDto(category);
        log.info("getById TrainingCategory completed successfully with: {}", kv("id", id));
        return responseDto;

    }

    @Override
    public TrainingCategoryResponseDto addCategory(TrainingCategoryRequestDto categoryRequestDto) {
        log.info("create TrainingCategory started with:{}", categoryRequestDto);
        TrainingCategory category = trainingCategoryMapper.dtoToEntity(categoryRequestDto);
        categoryRepository.save(category);
        TrainingCategoryResponseDto categoryResponseDto = trainingCategoryMapper.entityToDto(category);
        log.info("create TrainingCategory completed successfully with: {}", kv("category", categoryResponseDto));
        return categoryResponseDto;
    }

    @Override
    public TrainingCategoryResponseDto updateCategory(Long id, TrainingCategoryRequestDto categoryRequestDto) {
        log.info("update TrainingCategory started with: {}, {}", kv("id", id),
                kv("trainingCategoryRequestDto", categoryRequestDto));
        TrainingCategory category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(TrainingCategory.class, id);
        });
        if (category != null) {
            category = trainingCategoryMapper.dtoToEntity(categoryRequestDto);
            category.setId(id);
        }
        categoryRepository.save(category);
        TrainingCategoryResponseDto responseDto = trainingCategoryMapper.entityToDto(category);
        log.info("update TrainingCategory completed successfully with: {}, {}", kv("id", id),
                kv("category", responseDto));
        return responseDto;
    }

    @Override
    public TrainingCategoryResponseDto deleteCategory(Long id) {
        log.info("Delete TrainingCategory started with: {}", kv("id", id));
        TrainingCategory category = categoryRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(TrainingCategory.class, id);
                }
        );
        categoryRepository.delete(category);
        TrainingCategoryResponseDto responseDto = trainingCategoryMapper.entityToDto(category);
        log.info("delete TrainingCategory completed successfully with: {}", kv("id", id));
        return responseDto;
    }


}
