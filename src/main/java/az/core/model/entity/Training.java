package az.core.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Training implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String url;
    private String name;
    private String cost;
    private String time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainingCategoryId", nullable = false)
    private TrainingCategory trainingCategory;
    private String description;
    private String trainingMethod;
    private String trainingProgram;
    private Boolean status;
    private String image;

}
