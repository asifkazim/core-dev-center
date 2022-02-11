package az.core.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainingRequestDto {
    private String name;
    private String url;
    private String cost;
    private String time;
    private Long categoryId;
    private String description;
    private String trainingMethod;
    private String trainingProgram;
    private Boolean status;
}
