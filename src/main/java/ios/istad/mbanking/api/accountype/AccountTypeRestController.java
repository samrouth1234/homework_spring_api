package ios.istad.mbanking.api.accountype;

import ios.istad.mbanking.BaseRest.BaseRest;
import ios.istad.mbanking.api.accountype.AccDto.AccountTypeDto;
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
    // find All element
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
    // find by id
    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        var accountTypeDto =accountTypeService.findAccountById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been create successfully!")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }
    @PostMapping
    public BaseRest<?>createNewAccount( @Valid @RequestBody  AccountTypeDto body){
        AccountTypeDto accountTypeDto=accountTypeService.createNewAccount(body);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account Create have been successfully")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }
    @PutMapping("/{id}")
    public BaseRest<?> updateAccountTypeById ( @PathVariable Integer id, @Valid  @RequestBody  AccountTypeDto body){
        AccountTypeDto accountTypeDto=accountTypeService.updateAccountById(id,body);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account Type Update have been successfully")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteById(@PathVariable Integer id){
        Integer deleteId =accountTypeService.deleteAccountById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been deleted successfully!")
                .timestamp(LocalDateTime.now())
                .data(deleteId)
                .build();
    }

}
