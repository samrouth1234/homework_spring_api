package ios.istad.mbanking.api.user.webs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateUserDto(@NotBlank (message = "Name is requires..!") String name,
                            @NotBlank (message = "gender is requires..!")
                            String gender,
                            String oneSignalId,
                            String studentCardId,
                            @NotNull(message = "isStudent is requires..!")
                            Boolean isStudent) {
}
