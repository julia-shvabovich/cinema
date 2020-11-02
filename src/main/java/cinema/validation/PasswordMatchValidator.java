package cinema.validation;

import cinema.model.dto.user.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator
        implements ConstraintValidator<FieldsValueMatch, UserRequestDto> {
    public boolean isValid(UserRequestDto value, ConstraintValidatorContext context) {
        String password = value.getPassword();
        String passwordRepeated = value.getPasswordRepeated();
        return Objects.equals(password, passwordRepeated);
    }
}
