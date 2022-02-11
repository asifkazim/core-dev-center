package az.core.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Inquiry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String url;
    private String name;
    private String surname;
    private String organization;
    private String position;
    private String email;
    private String mobilePhoneNumber;
    private String training;
}
