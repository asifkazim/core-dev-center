package az.core.service.impl;

import az.core.error.EntityNotFoundException;
import az.core.error.FileCantUploadException;
import az.core.mapper.BlogMapper;
import az.core.model.dto.request.BlogRequestDto;
import az.core.model.dto.response.BlogResponseDto;
import az.core.model.entity.Blog;
import az.core.model.entity.BlogCategory;
import az.core.repository.BlogCategoryRepository;
import az.core.repository.BlogRepository;
import az.core.service.BlogService;
import az.core.service.FilesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final FilesService filesService;
    private final BlogCategoryRepository blogCategoryRepository;
    private final BlogMapper blogMapper;

    @Value("${minio.image-folder}")
    private String imageFolder;

    @Override
    public List<BlogResponseDto> getAllBlogs() {
        log.info("getAllBlogs requesting...");
        List<Blog> blogs = blogRepository.findAll();
        log.info("getAllBlogs Response started with: {}", kv("blogs", blogs));
        return blogMapper.entitiesToDto(blogs);
    }

    @Override
    public BlogResponseDto getById(Long id) {
        log.info("getById Blog started with: {}", kv("id", id));
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Blog.class, id);
        });
        BlogResponseDto blogDto = blogMapper.entityToDto(blog);
        log.info("getById Blog completed successfully with: {}", kv("id", id));
        return blogDto;
    }


    @Override
    @Transactional
    public BlogResponseDto addBlog(BlogRequestDto blogRequestDto) {
        log.info("create Blog started with:{}", blogRequestDto);
        log.info("Find Category By Name started with: {}", kv("name", blogRequestDto.getBlogCategory()));
        BlogCategory category = blogCategoryRepository.findByName(blogRequestDto.getBlogCategory()).orElseThrow(() -> {
            throw new EntityNotFoundException(BlogCategory.class);
        });
        log.info("Find Category By Name Response started with: {}", kv("name", blogRequestDto.getBlogCategory()));
        Blog blog = blogMapper.dtoToEntity(blogRequestDto);
        blog.setBlogCategory(category);
        blogRepository.save(blog);
        BlogResponseDto blogResponseDto = blogMapper.entityToDto(blog);
        log.info("create Blog completed successfully with: {}", kv("blogDto", blogResponseDto));
        return blogResponseDto;
    }

    @Override
    public BlogResponseDto updateBlog(Long id, BlogRequestDto blogRequestDto) {
        log.info("update Blog started with: {}, {}", kv("id", id),
                kv("blogRequestDto", blogRequestDto));
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Blog.class, id);
        });

        if (blog != null) {
            blog = blogMapper.dtoToEntity(blogRequestDto);
            blog.setId(id);
            log.info("Find Category By Name Response started with: {}", kv("name", blogRequestDto.getBlogCategory()));
            BlogCategory category = blogCategoryRepository.findByName(blogRequestDto.getBlogCategory()).orElseThrow(() -> {
                throw new EntityNotFoundException(BlogCategory.class);
            });
            blog.setBlogCategory(category);
        }
        blogRepository.save(blog);
        BlogResponseDto responseDto = blogMapper.entityToDto(blog);
        log.info("update Blog completed successfully with: {}, {}", kv("id", id),
                kv("blogDto", responseDto));
        return responseDto;

    }

    @Override
    public BlogResponseDto deleteBlog(Long id) {
        log.info("delete Blog started with: {}", kv("id", id));
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(Blog.class, id);
                }
        );
        if (blog.getImage() != null) {
            deleteFile(blog.getImage(), imageFolder);
        }
        blogRepository.delete(blog);
        BlogResponseDto blogDto = blogMapper.entityToDto(blog);
        log.info("delete Blog completed successfully with: {}", kv("id", id));
        return blogDto;
    }


    @Override
    public String uploadImage(MultipartFile file, Long id) {
        log.info("uploadImage to Blog started with, partnerId: {}", id);
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Blog.class, id));
        if (blog.getImage() == null) {
            String fileName = filesService.uploadImage(file, imageFolder);
            blog.setImage(fileName);
            blogRepository.save(blog);
            log.info("uploadImage to Blog completed successfully with partnerId:{}", id);
            return fileName;
        }
        throw new FileCantUploadException(file.getOriginalFilename());
    }


    @Override
    public String updateImage(MultipartFile file, Long id) {
        log.info("updateImage to Blog started with, {}",
                kv("partnerId", id));
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Blog.class, id));
        deleteFile(blog.getImage(), imageFolder);
        String fileName = filesService.uploadImage(file, imageFolder);
        blog.setImage(fileName);
        blogRepository.save(blog);
        log.info("updateImage to Blog completed successfully with {}",
                kv("partnerId", blog));
        return fileName;
    }

    @Override
    public void deleteUserImage(Long id) {
        log.info("deleteUserImage started from Blog with {}", kv("id", id));
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Blog.class, id));
        if (blog.getImage() != null) {
            filesService.deleteFile(blog.getImage(), imageFolder);
            blog.setImage(null);
            blogRepository.save(blog);
        }
        log.info("deleteUserImage completed successfully from Blog with {} ", kv("id", id));
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
