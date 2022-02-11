package az.core.controller;

import az.core.model.dto.request.BlogRequestDto;
import az.core.model.dto.response.BlogResponseDto;
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
    public ResponseEntity<List<BlogResponseDto>> getAllBlogs() {
        List<BlogResponseDto> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponseDto> getById(@PathVariable Long id) {
        BlogResponseDto blog = blogService.getById(id);
        return ResponseEntity.ok(blog);
    }

    @GetMapping("/urls/{url}")
    public ResponseEntity<BlogResponseDto> getByUrl(@PathVariable String url) {
        BlogResponseDto blog = blogService.getByUrl(url);
        return ResponseEntity.ok(blog);
    }

    @PostMapping
    public ResponseEntity<BlogResponseDto> addBlog(@Valid @RequestBody BlogRequestDto blogRequestDto) throws Exception {
        BlogResponseDto blog = blogService.addBlog(blogRequestDto);
        return ResponseEntity.ok(blog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponseDto> updateBlog(@PathVariable("id") Long id, @RequestBody BlogRequestDto blogRequestDto) {
        BlogResponseDto blog = blogService.updateBlog(id, blogRequestDto);
        return ResponseEntity.ok(blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogResponseDto> deleteBlog(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.deleteBlog(id));
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
