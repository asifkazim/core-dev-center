package az.core.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
public class BlogDto {

    private Long id;
    private String publicationDate;
    private String description;
    private String blogCategory;
    private String status;
    private MultipartFile image;

}
