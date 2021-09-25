package az.core.model.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue
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
