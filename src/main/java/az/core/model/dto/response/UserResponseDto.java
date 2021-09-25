package az.core.model.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String organization;
    private String position;
    private String email;
    private String number;
    private String course;
}
