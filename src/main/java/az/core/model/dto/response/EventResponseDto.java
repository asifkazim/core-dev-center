package az.core.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventResponseDto {
    private Long id;
    private String url;
    private String name;
    private String startTime;
    private String payment;
    private String period;
    private String place;
    private String description;
    private String moderator;
    private Boolean status;
    private String image;
}
