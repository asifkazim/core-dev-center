package az.core.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseRequestDto {
    private String name;
    private String cost;
    private String time;
    private String courseCategory;
    private String description;
    private String courseMethod;
    private String courseProgram;
    private Boolean active;
}
