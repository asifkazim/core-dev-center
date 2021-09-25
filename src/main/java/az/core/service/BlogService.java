package az.core.service;

import az.core.model.dto.request.BlogRequestDto;
import az.core.model.dto.response.BlogResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BlogService {

    List<BlogResponseDto> getAllBlogs();

    BlogResponseDto getById(Long id);

    BlogResponseDto addBlog(BlogRequestDto blogRequestDto) throws Exception;

    BlogResponseDto updateBlog(Long id, BlogRequestDto blogRequestDto);

    BlogResponseDto deleteBlog(Long id);

    String uploadImage(MultipartFile file, Long id);

    String updateImage(MultipartFile file, Long id);

    void deleteUserImage(Long id);

    void deleteFile(String fileName, String folder);

    byte[] getFile(String fileName, String folder);


}
