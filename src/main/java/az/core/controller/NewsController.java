package az.core.controller;

import az.core.model.dto.FileDto;
import az.core.model.dto.request.EventRequestDto;
import az.core.model.dto.request.NewsRequestDto;
import az.core.model.dto.response.EventResponseDto;
import az.core.model.dto.response.NewsResponseDto;
import az.core.service.NewsService;
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
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Value("${minio.image-folder}")
    private String imageFolder;

    @GetMapping
    public ResponseEntity<List<NewsResponseDto>> getAllNews() {
        List<NewsResponseDto> news = newsService.getAllNews();
        return ResponseEntity.ok(news);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDto> getById(@PathVariable Long id) {
        NewsResponseDto news = newsService.getById(id);
        return ResponseEntity.ok(news);
    }

    @GetMapping("/urls/{url}")
    public ResponseEntity<NewsResponseDto> getById(@PathVariable String url) {
        NewsResponseDto news = newsService.getByUrl(url);
        return ResponseEntity.ok(news);
    }

    @PostMapping
    public ResponseEntity<NewsResponseDto> addNews(@RequestBody NewsRequestDto newsRequestDto) {
        NewsResponseDto event = newsService.addNews(newsRequestDto);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponseDto> updateNews(@PathVariable Long id, @RequestBody NewsRequestDto newsRequestDto) {
        NewsResponseDto event = newsService.updateNews(id, newsRequestDto);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewsResponseDto> deleteNews(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(newsService.deleteNews(id));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping(value = "/image/{id}")
    public ResponseEntity<FileDto> createImage(@PathVariable Long id, @Valid @RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDto(newsService.uploadImage(file, id)));
    }


    @PutMapping(value = "/image/{id}")
    public ResponseEntity<FileDto> updateImage(@PathVariable Long id, @Valid @RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDto(newsService.updateImage(file, id)));
    }

    @GetMapping(value = "/image/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@PathVariable("fileName") String fileName) {
        return newsService.getFile(fileName, imageFolder);
    }

    @DeleteMapping("/image/{id}")
    public void deleteUserImage(@PathVariable("id") Long id) {
        newsService.deleteUserImage(id);
    }

}
