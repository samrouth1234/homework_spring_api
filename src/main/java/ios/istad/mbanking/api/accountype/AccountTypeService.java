package ios.istad.mbanking.api.accountype;
import ios.istad.mbanking.api.accountype.AccDto.AccountTypeDto;
import ios.istad.mbanking.api.accountype.AccDto.CreateAccountDto;
import ios.istad.mbanking.api.user.webs.UserDto;

import java.util.List;
public interface AccountTypeService {
    List<AccountTypeDto> findAllAccount();
    AccountTypeDto createNewAccount(CreateAccountDto createAccountDto);

    AccountTypeDto findAccountById(Integer id);
    Integer deleteAccountById(Integer id);

}
