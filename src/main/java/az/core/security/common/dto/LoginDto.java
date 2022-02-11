package az.core.security.common.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString(exclude = "password")
public class LoginDto {

    @NotNull
    @Size(min = 1, max = 50)
    private String username;


    @NotNull
    @Size(min = 3,max = 10)
    private String password;
}
