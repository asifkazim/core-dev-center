package az.core.service.impl;

import az.core.error.BlogCategoryNotFoundException;
import az.core.error.BlogNotFoundException;
import az.core.mapper.BlogMapper;
import az.core.model.dto.BlogDto;
import az.core.model.entity.Blog;
import az.core.model.entity.BlogCategory;
import az.core.repository.BlogCategoryRepository;
import az.core.repository.BlogRepository;
import az.core.service.BlogService;
import az.core.util.ApplicationCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogCategoryRepository blogCategoryRepository;
    private final BlogMapper blogMapper;

    @Override
    public List<BlogDto> getAllBlogs() {
        log.info("Request, GET All Blogs");
        List<Blog> blogs = blogRepository.findAll();
        log.info("Response, blogs:{}", blogs);
        return blogMapper.entitiesToDto(blogs);
    }

    @Override
    public BlogDto getById(Long id) {
        log.info("Request, GET Blog By Id, id:{}", id);
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new BlogNotFoundException(ApplicationCode.BLOG_NOT_FOUND, "Blog not found!")
        );
        log.info("Response, Blog By Id, id:{}, blog:{}", id, blog);
        return blogMapper.entityToDto(blog);
    }

    @Override
    public BlogDto addBlog(BlogDto blogDto) throws Exception {
        log.info("Request, ADD Blog, blog:{}", blogDto);
        log.info("Request, GET Blog Category By Name, name:{}", blogDto.getBlogCategory());
        BlogCategory category = blogCategoryRepository.findByName(blogDto.getBlogCategory()).orElseThrow(
                () -> new BlogCategoryNotFoundException(ApplicationCode.BLOG_CATEGORY_NOT_FOUND, "Blog Category not found!")
        );
        if (category != null) {

            Blog blog = blogMapper.dtoToEntity(blogDto);
            blog.setBlogCategory(category);
            blogRepository.save(blog);
            log.info("Response, ADDED Blog, blog:{}", blog);
            return blogDto;
        } else {
            throw new Exception();
        }

    }

    @Override
    public BlogDto updateBlog(Long id, BlogDto blogDto) {
        log.info("Request, UPDATE Blog, id:{}, blog:{}", id, blogDto);
        log.info("Request, GET Blog By Id, id:{}", id);
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new BlogNotFoundException(ApplicationCode.BLOG_NOT_FOUND, "Blog not found!")
        );

        if (blog != null) {
            blog = blogMapper.dtoToEntity(blogDto);
            blog.setId(id);
            log.info("Request, GET Blog Category By Name, name:{}", blogDto.getBlogCategory());
            BlogCategory category = blogCategoryRepository.findByName(blogDto.getBlogCategory()).orElseThrow(
                    () -> new BlogCategoryNotFoundException(ApplicationCode.BLOG_CATEGORY_NOT_FOUND, "Blog Category not found!")
            );
            blog.setBlogCategory(category);
        }
        blogRepository.save(blog);
        log.info("Response, UPDATED Blog, id:{}, blog:{}", id, blog);
        return blogDto;

    }

    @Override
    public void deleteBlog(Long id) {
        log.info("Request, DELETE Blog, id:{}", id);
        blogRepository.deleteById(id);
        log.info("Response, DELETED Blog");
    }

}
