package ios.istad.mbanking.api.accountype;
import ios.istad.mbanking.api.accountype.AccDto.AccountTypeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {

    List<AccountTypeDto>toDtoList(List<AccountType> model);

    AccountTypeDto toAccountTypeDto (AccountType model);

    // cover object to accountType

    AccountType formAccountTypeDto(AccountTypeDto accountTypeDto);
}
