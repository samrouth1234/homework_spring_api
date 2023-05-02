package ios.istad.mbanking.api.accountype.AccDto;

import jakarta.validation.constraints.NotBlank;

public record AccountTypeDto(@NotBlank(message = "Name is requires..!") String name) {

}
