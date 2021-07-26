package az.core.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class CourseDto {
    private Long id;
    private String name;
    private String cost;
    private String time;
    private String courseCategory;
    private String description;
    private String courseMethod;
    private String courseProgram;
    private Boolean active;
    private String image;

}
