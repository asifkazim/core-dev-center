package az.core.controller;

import az.core.model.dto.request.CourseCategoryRequestDto;
import az.core.model.dto.response.CourseCategoryResponseDto;
import az.core.model.dto.response.CourseCategoryResponseDto;
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
    public ResponseEntity<List<CourseCategoryResponseDto>> getAllCategories() {
        List<CourseCategoryResponseDto> categories = courseCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseCategoryResponseDto> getById(@PathVariable Long id) {
        CourseCategoryResponseDto categoryDto = courseCategoryService.getById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping()
    public ResponseEntity<CourseCategoryResponseDto> addCourseCategory(@RequestBody CourseCategoryRequestDto courseCategoryRequestDto) {
        CourseCategoryResponseDto responseDto = courseCategoryService.addCategory(courseCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseCategoryResponseDto> updateCourseCategory(@PathVariable Long id, @RequestBody CourseCategoryRequestDto courseCategoryRequestDto) {
        CourseCategoryResponseDto responseDto = courseCategoryService.updateCategory(id, courseCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseCategoryResponseDto> deleteCourseCategory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseCategoryService.deleteCategory(id));
    }
}
