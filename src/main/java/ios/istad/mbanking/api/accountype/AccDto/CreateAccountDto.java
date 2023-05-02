package ios.istad.mbanking.api.accountype.AccDto;

import jakarta.validation.constraints.NotBlank;
public record CreateAccountDto(Integer id , @NotBlank(message = "Name is requires..!")String name) {
}
