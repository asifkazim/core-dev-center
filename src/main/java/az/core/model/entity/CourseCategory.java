package az.core.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CourseCategory {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany
    private List<Course> courses;
}
