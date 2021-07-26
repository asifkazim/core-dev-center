package az.core.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String header;
    private String message;
}
