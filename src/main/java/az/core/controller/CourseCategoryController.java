package az.core.controller;

import az.core.model.dto.CourseCategoryDto;
import az.core.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course/category")
@RequiredArgsConstructor
public class CourseCategoryController {

    private final CourseCategoryService courseCategoryService;

    @GetMapping
    public ResponseEntity<List<CourseCategoryDto>> getAllCourseCategories() {
        List<CourseCategoryDto> categories = courseCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseCategoryDto> getById(@PathVariable Long id) {
        CourseCategoryDto categoryDto = courseCategoryService.getById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping()
    public ResponseEntity<?> addCourseCategory(@RequestBody String categoryName) {
        courseCategoryService.addCategory(categoryName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public CourseCategoryDto updateCourseCategory(@PathVariable Long id, @RequestBody CourseCategoryDto courseCategoryDto) {
        return courseCategoryService.updateCategory(id, courseCategoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseCategory(@PathVariable Long id) {
        courseCategoryService.deleteCategory(id);
    }
}
