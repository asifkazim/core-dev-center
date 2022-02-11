package az.core.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class News implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String url;
    private String title;
    private String subTitle;
    private String content;
    private String date;
    private String status;
    private String image;
}
