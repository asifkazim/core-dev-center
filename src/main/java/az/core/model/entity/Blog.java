package az.core.model.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
