package az.core.service.impl;

import az.core.mapper.CourseCategoryMapper;
import az.core.model.dto.CourseCategoryDto;
import az.core.model.entity.CourseCategory;
import az.core.repository.CourseCategoryRepository;
import az.core.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryRepository categoryRepository;
    private final CourseCategoryMapper courseCategoryMapper;


    @Override
    public List<CourseCategoryDto> getAllCategories() {
        List<CourseCategory> category = categoryRepository.findAll();
        return courseCategoryMapper.entitiesToDto(category);
    }

    @Override
    public CourseCategoryDto getById(Long id) {
        CourseCategory category = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return courseCategoryMapper.entityToDto(category);
    }

    @Override
    public void addCategory(String categoryName) {
        CourseCategory courseCategory;
        courseCategory = new CourseCategory();
        courseCategory.setName(categoryName);
        categoryRepository.save(courseCategory);
    }

    @Override
    public CourseCategoryDto updateCategory(Long id, CourseCategoryDto courseCategoryDto) {

        CourseCategory courseCategory;
        courseCategory = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (courseCategory != null) {
            courseCategory = courseCategoryMapper.dtoToEntity(courseCategoryDto);
        }
        categoryRepository.save(courseCategory);

        return courseCategoryDto;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
