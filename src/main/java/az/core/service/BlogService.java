package az.core.service;

import az.core.model.dto.BlogDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BlogService {

    List<BlogDto> getAllBlogs();

    BlogDto getById(Long id);

    BlogDto addBlog(BlogDto blogDto) throws Exception;

    BlogDto updateBlog(Long id, BlogDto blogDto);

    BlogDto deleteBlog(Long id);

    String uploadImage(MultipartFile file, Long id);

    String updateImage(MultipartFile file, Long id);

    void deleteUserImage(Long id);

    void deleteFile(String fileName, String folder);

    byte[] getFile(String fileName, String folder);


}
