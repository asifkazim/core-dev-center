package az.core.service.impl;

import az.core.error.EntityNotFoundException;
import az.core.mapper.CourseCategoryMapper;
import az.core.model.dto.response.CourseCategoryResponseDto;
import az.core.model.dto.request.CourseCategoryRequestDto;
import az.core.model.entity.CourseCategory;
import az.core.repository.CourseCategoryRepository;
import az.core.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryRepository categoryRepository;
    private final CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryResponseDto> getAllCategories() {
        log.info("getAllCourseCategory requesting...");
        List<CourseCategory> category = categoryRepository.findAll();
        log.info("getAllCourseCategory Response started with: {}", kv("category", category));
        return courseCategoryMapper.entitiesToDto(category);
    }

    @Override
    public CourseCategoryResponseDto getById(Long id) {
        log.info("getById CourseCategory started with: {}", kv("id", id));
        CourseCategory category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(CourseCategory.class, id);
        });
        CourseCategoryResponseDto responseDto = courseCategoryMapper.entityToDto(category);
        log.info("getById CourseCategory completed successfully with: {}", kv("id", id));
        return responseDto;

    }

    @Override
    public CourseCategoryResponseDto addCategory(CourseCategoryRequestDto categoryRequestDto) {
        log.info("create CourseCategory started with:{}", categoryRequestDto);
        CourseCategory category = courseCategoryMapper.dtoToEntity(categoryRequestDto);
        categoryRepository.save(category);
        CourseCategoryResponseDto categoryResponseDto = courseCategoryMapper.entityToDto(category);
        log.info("create CourseCategory completed successfully with: {}", kv("category", categoryResponseDto));
        return categoryResponseDto;
    }

    @Override
    public CourseCategoryResponseDto updateCategory(Long id, CourseCategoryRequestDto categoryRequestDto) {
        log.info("update CourseCategory started with: {}, {}", kv("id", id),
                kv("courseCategoryRequestDto", categoryRequestDto));
        CourseCategory category = categoryRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(CourseCategory.class, id);
        });
        if (category != null) {
            category = courseCategoryMapper.dtoToEntity(categoryRequestDto);
            category.setId(id);
        }
        categoryRepository.save(category);
        CourseCategoryResponseDto responseDto = courseCategoryMapper.entityToDto(category);
        log.info("update CourseCategory completed successfully with: {}, {}", kv("id", id),
                kv("category", responseDto));
        return responseDto;
    }

    @Override
    public CourseCategoryResponseDto deleteCategory(Long id) {
        log.info("Delete CourseCategory started with: {}", kv("id", id));
        CourseCategory category = categoryRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(CourseCategory.class, id);
                }
        );
        categoryRepository.delete(category);
        CourseCategoryResponseDto responseDto = courseCategoryMapper.entityToDto(category);
        log.info("delete CourseCategory completed successfully with: {}", kv("id", id));
        return responseDto;
    }


}
