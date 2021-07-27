package az.core.service.impl;

import az.core.error.CourseCategoryNotFoundException;
import az.core.mapper.CourseCategoryMapper;
import az.core.model.dto.CourseCategoryDto;
import az.core.model.entity.CourseCategory;
import az.core.repository.CourseCategoryRepository;
import az.core.service.CourseCategoryService;
import az.core.util.ApplicationCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryRepository categoryRepository;
    private final CourseCategoryMapper courseCategoryMapper;


    @Override
    public List<CourseCategoryDto> getAllCategories() {
        log.info("Request, GET All Course Categories");
        List<CourseCategory> categories = categoryRepository.findAll();
        log.info("Response, course categories:{}", categories);
        return courseCategoryMapper.entitiesToDto(categories);
    }

    @Override
    public CourseCategoryDto getById(Long id) {
        log.info("Request, GET Course Category By Id, id:{}", id);
        CourseCategory category = categoryRepository.findById(id).orElseThrow(
                () -> new CourseCategoryNotFoundException(ApplicationCode.COURSE_CATEGORY_NOT_FOUND, "Course category not found!")
        );
        log.info("Response, Course Category By Id, id:{}, course category:{} ", id, category);
        return courseCategoryMapper.entityToDto(category);
    }

    @Override
    public void addCategory(String categoryName) {
        log.info("Request, ADD Course Category, category name:{}", categoryName);
        CourseCategory courseCategory;
        courseCategory = new CourseCategory();
        courseCategory.setName(categoryName);
        categoryRepository.save(courseCategory);
        log.info("Response, ADDED Course Category");
    }

    @Override
    public CourseCategoryDto updateCategory(Long id, CourseCategoryDto courseCategoryDto) {
        log.info("Request, UPDATE Course Category, id:{}, course category:{}", id, courseCategoryDto);
        CourseCategory courseCategory;
        courseCategory = categoryRepository.findById(id).orElseThrow(
                () -> new CourseCategoryNotFoundException(ApplicationCode.COURSE_CATEGORY_NOT_FOUND, "Course category not found!")
        );

        if (courseCategory != null) {
            courseCategory = courseCategoryMapper.dtoToEntity(courseCategoryDto);
        }
        categoryRepository.save(courseCategory);
        log.info("Response, UPDATED Blog Category, id:{}, course category:{}", id, courseCategory);
        return courseCategoryDto;
    }

    @Override
    public void deleteCategory(Long id) {
        log.info("Request, DELETE Course Category, id:{}", id);
        categoryRepository.deleteById(id);
        log.info("Response, DELETED Course Category");
    }
}
