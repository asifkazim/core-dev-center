package az.core.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class TrainingCategory implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String url;
    @OneToMany(mappedBy = "trainingCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Training> trainings;
}
