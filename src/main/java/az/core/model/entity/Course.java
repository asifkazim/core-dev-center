package az.core.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String cost;
    private String time;
    @ManyToOne(fetch = FetchType.LAZY)
    private CourseCategory courseCategory;
    private String description;
    private String courseMethod;
    private String courseProgram;
    private Boolean active;
    private String image;

}
