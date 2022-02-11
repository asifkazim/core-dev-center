package az.core.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String url;
    private String name;
    private String startTime;
    private String payment;
    private String period;
    private String moderator;
    private String place;
    private String description;
    private String status;
    private String image;
}
