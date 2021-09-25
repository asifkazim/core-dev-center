package az.core.model.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogRequestDto {

    private String publicationDate;
    private String description;
    private String title;
    private String blogCategory;
    private Boolean status;
}
