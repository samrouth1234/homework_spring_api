package ios.istad.mbanking.api.accountype;

import ios.istad.mbanking.BaseRest.BaseRest;
import ios.istad.mbanking.api.accountype.AccDto.AccountTypeDto;
import ios.istad.mbanking.api.accountype.AccDto.CreateAccountDto;
import ios.istad.mbanking.api.user.webs.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
@RestController
public class AccountTypeRestController {
    private final AccountTypeService accountTypeService;

    // find by id
    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        AccountTypeDto accountTypeDto =accountTypeService.findAccountById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been create successfully!")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }
    @GetMapping
    public BaseRest<?>findAll(){
        var accountTypeDtoList = accountTypeService.findAllAccount();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account types have been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtoList)
                .build();
    }
    @PostMapping
    public BaseRest<?>createNewAccount(@RequestBody @Valid CreateAccountDto createAccountDto){
        AccountTypeDto accountTypeDto=accountTypeService.createNewAccount(createAccountDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account Create have been successfully")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }

}
