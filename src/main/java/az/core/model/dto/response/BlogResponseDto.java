package az.core.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogResponseDto {
    private Long id;
    private String url;
    private String publicationDate;
    private String description;
    private String title;
    private Long categoryId;
    private Boolean status;
    private String image;
}
