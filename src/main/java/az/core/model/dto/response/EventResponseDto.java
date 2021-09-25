package az.core.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventResponseDto {
    private Long id;
    private String name;
    private String startTime;
    private String payment;
    private String period;
    private String place;
    private String description;
    private String remainingPeriod;
    private String moderator;
    private String status;
    private String image;
}
