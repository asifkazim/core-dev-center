package az.core.controller;

import az.core.model.dto.BlogCategoryDto;
import az.core.service.BlogCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs/category")
@RequiredArgsConstructor

public class BlogCategoryController {

    private final BlogCategoryService blogCategoryService;

    @GetMapping
    public ResponseEntity<List<BlogCategoryDto>> getAllCategories() {
        List<BlogCategoryDto> categories = blogCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogCategoryDto> getById(@PathVariable Long id) {
        BlogCategoryDto categoryDto = blogCategoryService.getById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping()
    public ResponseEntity<?> addBlogCategory(@RequestBody String categoryName) {
        blogCategoryService.addCategory(categoryName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public BlogCategoryDto updateBlogCategory(@PathVariable Long id, @RequestBody BlogCategoryDto blogCategoryDto) {
        return blogCategoryService.updateCategory(id, blogCategoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBlogCategory(@PathVariable Long id) {
        blogCategoryService.deleteCategory(id);
    }
}
