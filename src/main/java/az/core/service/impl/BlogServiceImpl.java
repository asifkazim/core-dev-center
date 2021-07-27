package az.core.service.impl;

import az.core.mapper.BlogMapper;
import az.core.model.dto.BlogDto;
import az.core.model.entity.Blog;
import az.core.model.entity.BlogCategory;
import az.core.repository.BlogCategoryRepository;
import az.core.repository.BlogRepository;
import az.core.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogCategoryRepository blogCategoryRepository;
    private final BlogMapper blogMapper;

    @Override
    public List<BlogDto> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogMapper.entitiesToDto(blogs);
    }

    @Override
    public BlogDto getById(Long id) {

        Blog blog = blogRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return blogMapper.entityToDto(blog);
    }

    @Override
    public BlogDto addBlog(BlogDto blogDto) throws Exception {
        BlogCategory category = blogCategoryRepository.findByName(blogDto.getBlogCategoryDto());
        if (category != null) {

            Blog blog = blogMapper.dtoToEntity(blogDto);
            blog.setBlogCategory(category);
            blogRepository.save(blog);
            return blogDto;
        } else {
            throw new Exception();
        }

    }

    @Override
    public BlogDto updateBlog(Long id, BlogDto blogDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (blog != null) {
            blog = blogMapper.dtoToEntity(blogDto);
            blog.setId(id);

            BlogCategory category = blogCategoryRepository.findByName(blogDto.getBlogCategoryDto());
            blog.setBlogCategory(category);
        }
        blogRepository.save(blog);
        return blogDto;

    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

}
