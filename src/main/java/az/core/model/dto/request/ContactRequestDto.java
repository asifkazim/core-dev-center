package az.core.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactRequestDto {
    private String url;
    private String name;
    private String surname;
    private String email;
    private String header;
    private String message;
}
