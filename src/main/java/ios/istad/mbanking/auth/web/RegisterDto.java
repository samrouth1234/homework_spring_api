package ios.istad.mbanking.auth.web;

import ios.istad.mbanking.api.user.validator.email.EmailUnique;
import ios.istad.mbanking.api.user.validator.password.PasswordConstraint;
import ios.istad.mbanking.api.user.validator.password.PasswordMatch;
import ios.istad.mbanking.api.user.validator.roles.RoleIdConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@PasswordMatch(password = "password",confirmedPassword = "confirmedPassword")
public record RegisterDto(

        @NotBlank(message = "Email is required")
        @EmailUnique
        @Email
        String email,
        @NotBlank(message = "Password is required")
        @PasswordConstraint
        String password,
        @NotBlank(message = "Password is Confirmed")
        @PasswordConstraint
        String confirmedPassword,

        @NotNull(message = "roles are required !")
        @RoleIdConstraint
        List<Integer> roleIds) {

}
