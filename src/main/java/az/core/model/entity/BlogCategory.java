package az.core.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
public class BlogCategory implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "blogCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Blog> blogs;

}
