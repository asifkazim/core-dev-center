package az.core.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Course implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String cost;
    private String time;
    @JoinColumn
    @ManyToOne
    private CourseCategory courseCategory;
    private String description;
    private String courseMethod;
    private String courseProgram;
    private Boolean active;
    @Transient
    private String image;

}
