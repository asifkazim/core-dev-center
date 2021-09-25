package az.core.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String header;
    private String message;

}
