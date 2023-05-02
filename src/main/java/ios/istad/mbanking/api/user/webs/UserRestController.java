package ios.istad.mbanking.api.user.webs;

import com.github.pagehelper.PageInfo;
import ios.istad.mbanking.BaseRest.BaseError;
import ios.istad.mbanking.BaseRest.BaseRest;
import ios.istad.mbanking.api.user.User;
import ios.istad.mbanking.api.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PutMapping("/{id}")
    public BaseRest<?> updateUserById(@PathVariable ("id") Integer id ,@RequestBody UpdateUserDto updateUserDto ){
        UserDto  userDto =userService.updateUserById(id ,updateUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully!")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

    @PutMapping("/{id}/update-is-deleted-status")
    public BaseRest<?> updateIsDeleteStatus(@PathVariable Integer id ,@RequestBody isDeletedDto dto ){  //PathVariable unine
        Integer deleteId =userService.updateIsDeleteStatus(id ,dto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully!")
                .timestamp(LocalDateTime.now())
                .data(deleteId)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id){//PathVariable unine
        Integer deleteID =userService.deleteUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully!")
                .timestamp(LocalDateTime.now())
                .data(deleteID)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        UserDto userDto=userService.findUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been create successfully!")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
    @GetMapping
    public BaseRest<?>findAllUser(@RequestParam(name="page",required = false,defaultValue = "1")int page,
                                  @RequestParam(value = "limit",required = false,defaultValue = "20")int limit){
        PageInfo<UserDto> userPageInfo =userService.findAllUsers(page,limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been found successfully!")
                .timestamp(LocalDateTime.now())
                .data(userPageInfo)
                .build();
    }
    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto){
        UserDto userDto=userService.createNewUser(createUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been create successfully!")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
}
