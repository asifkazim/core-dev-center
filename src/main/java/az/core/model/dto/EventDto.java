package az.core.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {
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
    private MultipartFile image;
}
