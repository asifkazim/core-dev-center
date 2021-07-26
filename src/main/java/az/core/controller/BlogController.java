package az.core.controller;

import az.core.model.dto.BlogDto;
import az.core.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        List<BlogDto> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getById(@PathVariable Long id) {
        BlogDto blog = blogService.getById(id);
        return ResponseEntity.ok(blog);
    }

    @PostMapping
    public ResponseEntity<BlogDto> addBlog(@RequestBody BlogDto blogDto) throws Exception {
        BlogDto blog = blogService.addBlog(blogDto);
        return ResponseEntity.ok(blog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Long id, @RequestBody BlogDto blogDto) {
        BlogDto blog = blogService.updateBlog(id, blogDto);
        return ResponseEntity.ok(blog);
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
    }

}
