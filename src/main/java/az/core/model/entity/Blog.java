package az.core.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "blog")
@Data
@NoArgsConstructor
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String url;
    private String title;
    private String subTitle;
    private String description;
    private String publicationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blogCategoryId", nullable = false)
    private BlogCategory blogCategory;
    private Boolean status;
    private String image;



}
