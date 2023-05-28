package ios.istad.mbanking.api.user.validator.roles;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoleUniqueConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface RoleIdConstraint {
    String message() default "Roles ID is NOT Existed";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
