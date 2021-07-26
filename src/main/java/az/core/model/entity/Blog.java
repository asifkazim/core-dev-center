package az.core.model.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Blog implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String publicationDate;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private BlogCategory blogCategory;
    private String status;
    @Transient
    private MultipartFile image;

    public Blog() {
    }

    public Blog(Long id, String publicationDate, String description, BlogCategory blogCategory, String status, MultipartFile image) {
        this.id = id;
        this.publicationDate = publicationDate;
        this.description = description;
        this.blogCategory = blogCategory;
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

    public BlogCategory getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(BlogCategory blogCategory) {
        this.blogCategory = blogCategory;
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

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", publicationDate='" + publicationDate + '\'' +
                ", description='" + description + '\'' +
                ", blogCategory=" + blogCategory +
                ", status='" + status + '\'' +
                ", image=" + image +
                '}';
    }
}
