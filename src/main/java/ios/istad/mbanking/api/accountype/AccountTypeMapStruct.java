package ios.istad.mbanking.api.accountype;

import ios.istad.mbanking.api.accountype.AccDto.AccountTypeDto;
import ios.istad.mbanking.api.accountype.AccDto.CreateAccountDto;
import ios.istad.mbanking.api.user.User;
import ios.istad.mbanking.api.user.webs.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {
    List<AccountTypeDto>toDtoList(List<AccountType> model);
    AccountType accountToAccountDto (CreateAccountDto createAccountDto);
    AccountTypeDto accountToAccountDto(AccountType accountType);
}
