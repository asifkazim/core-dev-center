package az.core.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsRequestDto {
    private String url;
    private String title;
    private String subTitle;
    private String content;
    private String date;
    private Boolean status;
}
