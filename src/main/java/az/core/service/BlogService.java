package az.core.service;

import az.core.model.dto.BlogDto;
import java.util.List;


public interface BlogService {

     List<BlogDto> getAllBlogs();

     BlogDto getById(Long id);

     BlogDto addBlog(BlogDto blogDto) throws Exception;

     BlogDto updateBlog(Long id,BlogDto blogDto);

     void deleteBlog(Long id);
}
