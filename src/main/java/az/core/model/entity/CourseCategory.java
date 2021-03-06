package az.core.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CourseCategory implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "courseCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> courses;
}
