package az.core.model.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogRequestDto {
    private String url;
    private String publicationDate;
    private String description;
    private String title;
    private Long categoryId;
    private Boolean status;
}
