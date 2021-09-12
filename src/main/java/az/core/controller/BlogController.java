package az.core.controller;

import az.core.model.dto.BlogDto;
import az.core.model.dto.FileDto;
import az.core.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @Value("${minio.image-folder}")
    private String imageFolder;

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
    public ResponseEntity<BlogDto> addBlog(@Valid @RequestBody BlogDto blogDto) throws Exception {
        BlogDto blog = blogService.addBlog(blogDto);
        return ResponseEntity.ok(blog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable Long id, @RequestBody BlogDto blogDto) {
        BlogDto blog = blogService.updateBlog(id, blogDto);
        return ResponseEntity.ok(blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogDto> deleteBlog(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.deleteBlog(id));
    }


    @PostMapping(value = "/image/{id}")
    public ResponseEntity<FileDto> createImage(@PathVariable Long id, @Valid @RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDto(blogService.uploadImage(file, id)));
    }


    @PutMapping(value = "/image/{id}")
    public ResponseEntity<FileDto> updateImage(@PathVariable Long id, @Valid @RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDto(blogService.updateImage(file, id)));
    }

    @GetMapping(value = "/image/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@PathVariable("fileName") String fileName) {
        return blogService.getFile(fileName, imageFolder);
    }

    @DeleteMapping("/image/{id}")
    public void deleteUserImage(@PathVariable("id") Long id) {
        blogService.deleteUserImage(id);
    }

}
