package az.core.controller;

import az.core.model.dto.request.BlogCategoryRequestDto;
import az.core.model.dto.response.BlogCategoryResponseDto;
import az.core.service.BlogCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs/categories")
@RequiredArgsConstructor

public class BlogCategoryController {

    private final BlogCategoryService blogCategoryService;

    @GetMapping
    public ResponseEntity<List<BlogCategoryResponseDto>> getAllCategories() {
        List<BlogCategoryResponseDto> categories = blogCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogCategoryResponseDto> getById(@PathVariable Long id) {
        BlogCategoryResponseDto categoryDto = blogCategoryService.getById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping()
    public ResponseEntity<BlogCategoryResponseDto> addBlogCategory(@RequestBody BlogCategoryRequestDto blogCategoryRequestDto) {
        BlogCategoryResponseDto responseDto = blogCategoryService.addCategory(blogCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogCategoryResponseDto> updateBlogCategory(@PathVariable Long id, @RequestBody BlogCategoryRequestDto blogCategoryRequestDto) {
        BlogCategoryResponseDto responseDto = blogCategoryService.updateCategory(id, blogCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogCategoryResponseDto> deleteBlogCategory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(blogCategoryService.deleteCategory(id));
    }
}
