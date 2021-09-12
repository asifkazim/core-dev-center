package az.core.model.entity;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publicationDate;
    private String tittleAz;
    private String tittleEn;
    private String descriptionAz;
    private String descriptionEn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blogCategoryId", nullable = false)
    private BlogCategory blogCategory;
    private Boolean status;
    private String image;

    public Blog() {
    }

    public Blog(Long id, String publicationDate, String tittleAz, String tittleEn, String descriptionAz, String descriptionEn, BlogCategory blogCategory, Boolean status, String image) {
        this.id = id;
        this.publicationDate = publicationDate;
        this.tittleAz = tittleAz;
        this.tittleEn = tittleEn;
        this.descriptionAz = descriptionAz;
        this.descriptionEn = descriptionEn;
        this.blogCategory = blogCategory;
        this.status = status;
        this.image = image;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getTittleAz() {
        return tittleAz;
    }

    public void setTittleAz(String tittleAz) {
        this.tittleAz = tittleAz;
    }

    public String getTittleEn() {
        return tittleEn;
    }

    public void setTittleEn(String tittleEn) {
        this.tittleEn = tittleEn;
    }

    public String getDescriptionAz() {
        return descriptionAz;
    }

    public void setDescriptionAz(String descriptionAz) {
        this.descriptionAz = descriptionAz;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public BlogCategory getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(BlogCategory blogCategory) {
        this.blogCategory = blogCategory;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
