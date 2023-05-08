package ios.istad.mbanking.api.accountype;

import ios.istad.mbanking.api.accountype.AccDto.AccountTypeDto;
import ios.istad.mbanking.api.user.User;
import ios.istad.mbanking.api.user.webs.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;
    @Override
    public List<AccountTypeDto> findAllAccount() {
        List<AccountType>accountTypes=accountTypeMapper.select();
        return accountTypeMapStruct.toDtoList(accountTypes);
    }

    @Override
    public AccountTypeDto createNewAccount(AccountTypeDto accountTypeDto) {
        AccountType accountType=accountTypeMapStruct.formAccountTypeDto(accountTypeDto);
        accountTypeMapper.insert(accountType);
        return accountTypeMapStruct.toAccountTypeDto(accountType);
    }

    @Override
    public AccountTypeDto findAccountById(Integer id) {
        AccountType accountType = accountTypeMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND
                        ,String.format("Account type  with %d id not found",id)));
        return accountTypeMapStruct.toAccountTypeDto(accountType);
    }

    @Override
    public AccountTypeDto updateAccountById(Integer id, AccountTypeDto accountTypeDto) {
        AccountType accountType = accountTypeMapStruct.formAccountTypeDto(accountTypeDto);
        accountType.setId(id);
        accountTypeMapper.update(accountType);
        return accountTypeMapStruct.toAccountTypeDto(accountType) ;
    }

    @Override
    public Integer deleteAccountById(Integer id) {
        boolean isFound =accountTypeMapper.existsById(id);
        if(isFound){
            // delete by id
            accountTypeMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                ,String.format("User with %d id not found",id));
    }
}
