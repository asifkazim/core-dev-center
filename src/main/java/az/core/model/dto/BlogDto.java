package az.core.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class BlogDto {

    private Long id;
    private String publicationDate;
    private MultiLang description;
    private MultiLang tittle;
    private String blogCategoryDto;
    private Boolean status;
    private MultipartFile image;

}
