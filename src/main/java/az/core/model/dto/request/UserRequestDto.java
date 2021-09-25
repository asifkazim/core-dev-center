package az.core.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    private String name;
    private String surname;
    private String organization;
    private String position;
    private String email;
    private String number;
    private String course;
}
