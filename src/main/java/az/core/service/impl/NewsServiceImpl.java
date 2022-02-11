package az.core.service.impl;

import az.core.error.EntityNotFoundException;
import az.core.error.FileCantUploadException;
import az.core.mapper.EventMapper;
import az.core.mapper.NewsMapper;
import az.core.model.dto.request.NewsRequestDto;
import az.core.model.dto.response.EventResponseDto;
import az.core.model.dto.response.NewsResponseDto;
import az.core.model.entity.Blog;
import az.core.model.entity.Event;
import az.core.model.entity.News;
import az.core.repository.EventRepository;
import az.core.repository.NewsRepository;
import az.core.service.FilesService;
import az.core.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final FilesService filesService;


    @Value("${minio.image-folder}")
    private String imageFolder;


    @Override
    public List<NewsResponseDto> getAllNews() {
        log.info("getAllNews requesting...");
        List<News> news = newsRepository.findAll();
        log.info("getAllNews Response started with: {}", kv("events", news));
        return newsMapper.entitiesToDto(news);
    }

    @Override
    public NewsResponseDto getById(Long id) {
        log.info("getById News started with: {}", kv("id", id));
        News news = newsRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(News.class, id);
        });
        NewsResponseDto response = newsMapper.entityToDto(news);
        log.info("getById News completed successfully with: {}", kv("id", id));
        return response;
    }


    @Override
    public NewsResponseDto getByUrl(String url) {
        log.info("getByUrl News started with: {}", kv("url", url));
        News news = newsRepository.findByUrl(url).orElseThrow(() -> {
            throw new EntityNotFoundException(News.class, url);
        });
        NewsResponseDto response = newsMapper.entityToDto(news);
        log.info("getByUrl News completed successfully with: {}", kv("url", url));
        return response;    }

    @Override
    public NewsResponseDto addNews(NewsRequestDto newsRequestDto) {
        log.info("create News started with:{}", newsRequestDto);
        News news = newsMapper.dtoToEntity(newsRequestDto);
        newsRepository.save(news);
        NewsResponseDto newsResponseDto = newsMapper.entityToDto(news);
        log.info("create News completed successfully with: {}", kv("response", newsResponseDto));
        return newsResponseDto;
    }

    @Override
    public NewsResponseDto updateNews(Long id, NewsRequestDto newsRequestDto) {
        log.info("update News started with: {}, {}", kv("id", id),
                kv("newsRequestDto", newsRequestDto));
        News news = newsRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(News.class, id);
        });

        if (news != null) {
            String image = news.getImage();
            news = newsMapper.dtoToEntity(newsRequestDto);
            news.setId(id);
            news.setImage(image);
        }
        newsRepository.save(news);
        NewsResponseDto responseDto = newsMapper.entityToDto(news);
        log.info("update News completed successfully with: {}, {}", kv("id", id),
                kv("response", responseDto));
        return responseDto;
    }

    @Override
    public NewsResponseDto deleteNews(Long id) {

        log.info("delete News started with: {}", kv("id", id));

        News news = newsRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(News.class, id);
                }
        );
        if (news.getImage() != null) {
            deleteFile(news.getImage(), imageFolder);
        }
        newsRepository.delete(news);
        NewsResponseDto eventResponseDto = newsMapper.entityToDto(news);
        log.info("delete News completed successfully with: {}", kv("id", id));
        return eventResponseDto;
    }

    @Override
    public String uploadImage(MultipartFile file, Long id) {
        log.info("uploadImage to News started with, partnerId: {}", id);
        News news = newsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(News.class, id));
        if (news.getImage() == null) {
            String fileName = filesService.uploadImage(file, imageFolder);
            news.setImage(fileName);
            newsRepository.save(news);
            log.info("uploadImage to News completed successfully with partnerId:{}", id);
            return fileName;
        }
        throw new FileCantUploadException(file.getOriginalFilename());
    }

    @Override
    public String updateImage(MultipartFile file, Long id) {
        log.info("updateImage to News started with, {}",
                kv("partnerId", id));
        News news = newsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(News.class, id));
        deleteFile(news.getImage(), imageFolder);
        String fileName = filesService.uploadImage(file, imageFolder);
        news.setImage(fileName);
        newsRepository.save(news);
        log.info("updateImage to News completed successfully with {}",
                kv("partnerId", news));
        return fileName;    }

    @Override
    public void deleteUserImage(Long id) {
        log.info("deleteUserImage started from News with {}", kv("id", id));
        News news = newsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(News.class, id));
        if (news.getImage() != null) {
            filesService.deleteFile(news.getImage(), imageFolder);
            news.setImage(null);
            newsRepository.save(news);
        }
        log.info("deleteUserImage completed successfully from News with {} ", kv("id", id));

    }

    @Override
    public void deleteFile(String fileName, String folder) {

    }

    @Override
    public byte[] getFile(String fileName, String folder) {
        log.info("getFile started with {}", kv("fileName", fileName));
        return filesService.getFile(fileName, folder);
    }

}
