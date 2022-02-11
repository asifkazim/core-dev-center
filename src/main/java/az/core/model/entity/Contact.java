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
public class Contact implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String url;
    private String name;
    private String surname;
    private String email;
    private String header;
    private String message;
}
