package ios.istad.mbanking.auth.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogInDto(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password) {

}
