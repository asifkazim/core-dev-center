package az.core.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventRequestDto {
    private String name;
    private String startTime;
    private String payment;
    private String period;
    private String place;
    private String description;
    private String remainingPeriod;
    private String moderator;
    private String status;
}
