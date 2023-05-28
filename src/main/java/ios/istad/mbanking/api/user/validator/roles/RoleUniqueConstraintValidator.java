package ios.istad.mbanking.api.user.validator.roles;

import ios.istad.mbanking.api.user.UsersMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class RoleUniqueConstraintValidator implements ConstraintValidator<RoleIdConstraint , List<Integer> >{

    private final UsersMapper usersMapper;
    @Override
    public boolean isValid( List<Integer> roleIds, ConstraintValidatorContext context) {
        for (Integer roleId :roleIds){
            if (!usersMapper.checkRoleId(roleId)){
                return false;
            }
        }
        return true;
    }
}
