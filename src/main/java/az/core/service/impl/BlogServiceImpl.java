package az.core.service.impl;

import az.core.error.EntityNotFoundException;
import az.core.error.FileCantUploadException;
import az.core.mapper.BlogMapper;
import az.core.model.dto.BlogDto;
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
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final FilesService filesService;
    private final BlogCategoryRepository blogCategoryRepository;
    private final BlogMapper blogMapper;

    @Value("${minio.image-folder}")
    private String imageFolder;

    @Override
    public List<BlogDto> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogMapper.entitiesToDto(blogs);
    }

    @Override
    public BlogDto getById(Long id) {
        log.info("getById Blog started with: {}", kv("id", id));
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Blog.class, id);
        });
        BlogDto blogDto = blogMapper.entityToDto(blog);
        log.info("getById Blog completed successfully with: {}", kv("id", id));
        return blogDto;
    }


    @Override
    public BlogDto addBlog(BlogDto blogDto) {
        System.out.println(blogDto);
        log.info("create User started with:{}", blogDto);
        log.info("Find Category By Name started with: {}", kv("name", blogDto.getBlogCategoryDto()));
        BlogCategory category = blogCategoryRepository.findByName(blogDto.getBlogCategoryDto()).orElseThrow(() -> {
            throw new EntityNotFoundException(BlogCategory.class);
        });
        log.info("Find Category By Name Response started with: {}", kv("name", blogDto.getBlogCategoryDto()));
        Blog blog = blogMapper.dtoToEntity(blogDto);
        blog.setBlogCategory(category);
        System.out.println(blog);
        blogRepository.save(blog);
        BlogDto blogResponseDto = blogMapper.entityToDto(blog);
        log.info("create Blog completed successfully with: {}", kv("blogDto", blogDto));
        return blogResponseDto;
    }

    @Override
    public BlogDto updateBlog(Long id, BlogDto blogDto) {
        log.info("update User started with: {}, {}", kv("id", id),
                kv("blogDto", blogDto));
        Blog blog = blogRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Blog.class, id);
        });

        if (blog != null) {
            blog = blogMapper.dtoToEntity(blogDto);
            blog.setId(id);
            log.info("Find Category By Name Response started with: {}", kv("name", blogDto.getBlogCategoryDto()));
            BlogCategory category = blogCategoryRepository.findByName(blogDto.getBlogCategoryDto()).orElseThrow(() -> {
                throw new EntityNotFoundException(BlogCategory.class);
            });
            blog.setBlogCategory(category);
        }
        blogRepository.save(blog);
        log.info("update User completed successfully with: {}, {}", kv("id", id),
                kv("blogDto", blogDto));
        return blogDto;

    }

    @Override
    public BlogDto deleteBlog(Long id) {
        log.info("delete Blog started with: {}", kv("id", id));
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()-> {
            throw new EntityNotFoundException(Blog.class, id);
        }
        );
        if (blog.getImage() != null) {
            deleteFile(blog.getImage(), imageFolder);
        }
        blogRepository.delete(blog);
        BlogDto blogDto = blogMapper.entityToDto(blog);
        log.info("delete Blog completed successfully with: {}", kv("id", id));
        return blogDto;
    }


    @Override
    @Transactional
    public String uploadImage(MultipartFile file, Long id) {
        log.info("uploadImage to User started with, partnerId: {}", id);

        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Blog.class, id));

        if (blog.getImage() == null) {
            String fileName = filesService.uploadImage(file, imageFolder);
            blog.setImage(fileName);
            blogRepository.save(blog);
            log.info("uploadImage to User completed successfully with partnerId:{}", id);
            return fileName;
        }
        throw new FileCantUploadException(file.getOriginalFilename());
    }


    @Override
    public String updateImage(MultipartFile file, Long id) {
        log.info("updateImage to User started with, {}",
                kv("partnerId", id));
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Blog.class, id));
        deleteFile(blog.getImage(), imageFolder);
        String fileName = filesService.uploadImage(file, imageFolder);
        blog.setImage(fileName);
        blogRepository.save(blog);
        log.info("updateImage to User completed successfully with {}",
                kv("partnerId", blog));
        return fileName;
    }

    @Override
    public void deleteUserImage(Long id) {
        log.info("deleteUserImage started from User with {}", kv("id", id));
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Blog.class, id));
        if (blog.getImage() != null) {
            filesService.deleteFile(blog.getImage(), imageFolder);
            blog.setImage(null);
            blogRepository.save(blog);
        }
        log.info("deleteUserImage completed successfully from User with {} ", kv("id", id));
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
