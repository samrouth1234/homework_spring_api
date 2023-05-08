package ios.istad.mbanking.api.accountype;
import ios.istad.mbanking.api.accountype.AccDto.AccountTypeDto;
import ios.istad.mbanking.api.user.webs.UpdateUserDto;

import java.util.List;
public interface AccountTypeService {
    List<AccountTypeDto> findAllAccount();

    AccountTypeDto createNewAccount(AccountTypeDto accountTypeDto);

    AccountTypeDto findAccountById(Integer id);

    AccountTypeDto updateAccountById(Integer id, AccountTypeDto accountTypeDto);

    Integer deleteAccountById(Integer id);

}
