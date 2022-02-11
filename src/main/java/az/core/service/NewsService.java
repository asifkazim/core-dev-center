package az.core.service;

import az.core.model.dto.request.NewsRequestDto;
import az.core.model.dto.response.NewsResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    List<NewsResponseDto> getAllNews();

    NewsResponseDto getById(Long id);

    NewsResponseDto addNews(NewsRequestDto newsRequestDto);

    NewsResponseDto updateNews(Long id, NewsRequestDto newsRequestDto);

    NewsResponseDto deleteNews(Long id);

    String uploadImage(MultipartFile file, Long id);

    String updateImage(MultipartFile file, Long id);

    void deleteUserImage(Long id);

    void deleteFile(String fileName, String folder);

    byte[] getFile(String fileName, String folder);

    NewsResponseDto getByUrl(String url);
}
