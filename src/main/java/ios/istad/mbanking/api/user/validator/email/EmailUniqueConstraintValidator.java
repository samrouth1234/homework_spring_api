package ios.istad.mbanking.api.user.validator.email;

import ios.istad.mbanking.api.user.UsersMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailUniqueConstraintValidator implements ConstraintValidator<EmailUnique,String > {
    private final UsersMapper usersMapper;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !usersMapper.existsByEmail(email);
    }
}
