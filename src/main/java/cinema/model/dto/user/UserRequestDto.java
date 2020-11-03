package cinema.model.dto.user;

import cinema.validation.Email;
import cinema.validation.FieldsValueMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@FieldsValueMatch
public class UserRequestDto {
    @Email
    private String email;
    @NotNull
    @Size(min = 4)
    private String password;
    @NotNull
    private String passwordRepeated;
}
