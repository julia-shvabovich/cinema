package cinema.model.dto.user;

import cinema.validation.FieldsValueMatch;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldsValueMatch
public class UserRequestDto {
    @Email
    private String email;
    @NotNull
    @Size(min = 4)
    private String password;
    @NotNull
    private String passwordRepeated;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }
}
