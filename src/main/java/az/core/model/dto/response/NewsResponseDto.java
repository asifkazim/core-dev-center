package az.core.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsResponseDto {

    private Long id;
    private String url;
    private String title;
    private String subTitle;
    private String content;
    private String date;
    private Boolean status;
    private String image;
}
