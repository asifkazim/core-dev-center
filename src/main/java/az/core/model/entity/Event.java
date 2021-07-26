package az.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {

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
    @Transient
    private MultipartFile image;
}
