package az.core.model.dto;

import az.core.model.entity.BlogCategory;
import org.springframework.web.multipart.MultipartFile;



public class BlogDto {

    private Long id;
    private String publicationDate;
    private String description;
    private String blogCategoryDto;
    private String status;
    private MultipartFile image;

    public BlogDto() {
    }

    public BlogDto(Long id, String publicationDate, String description, String blogCategoryDto, String status, MultipartFile image) {
        this.id = id;
        this.publicationDate = publicationDate;
        this.description = description;
        this.blogCategoryDto = blogCategoryDto;
        this.status = status;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlogCategoryDto() {
        return blogCategoryDto;
    }

    public void setBlogCategoryDto(String blogCategoryDto) {
        this.blogCategoryDto = blogCategoryDto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
