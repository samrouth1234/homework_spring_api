package ios.istad.mbanking.api.accountype;

import ios.istad.mbanking.api.accountype.AccDto.AccountTypeDto;
import ios.istad.mbanking.api.accountype.AccDto.CreateAccountDto;
import ios.istad.mbanking.api.user.User;
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
        List<AccountTypeDto> accountTypeDtoList = accountTypes.stream()
                .map(accountType -> new AccountTypeDto(accountType.getName()))
                .toList();
        return accountTypeDtoList;
    }

    public AccountTypeDto createNewAccount(CreateAccountDto createAccountDto) {
        AccountType accountTypes = accountTypeMapStruct .accountToAccountDto(createAccountDto);
        accountTypeMapper.insert(accountTypes);
        log.info("User ={}",accountTypes.getId());
        return this.findAccountById(accountTypes.getId());
    }

    @Override
    public AccountTypeDto findAccountById(Integer id) {
        AccountType accountType = accountTypeMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND
                        ,String.format("User with %d id not found",id)));
        return accountTypeMapStruct.accountToAccountDto(accountType);
    }

    @Override
    public Integer deleteAccountById(Integer id) {
        boolean isFound =accountTypeMapper.existsById(id);
        if(isFound){
            // delete by id
            accountTypeMapper.existsById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                ,String.format("User with %d id not found",id));
    }


}
