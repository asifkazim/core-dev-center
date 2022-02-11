package az.core.model.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class InquiryResponseDto {
    private Long id;
    private String url;
    private String name;
    private String surname;
    private String organization;
    private String position;
    private String email;
    private String mobilePhoneNumber;
    private String training;
}
