package ios.istad.mbanking.api.user;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ios.istad.mbanking.api.user.webs.CreateUserDto;
import ios.istad.mbanking.api.user.webs.UpdateUserDto;
import ios.istad.mbanking.api.user.webs.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UsersMapper usersMapper;
    private final UserMapStruct userMapStruct;

    // insert element to database
    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user= userMapStruct.createUserDtoToUser(createUserDto);
        usersMapper.insert(user);
        log.info("User ={}",user.getId());
        return this.findUserById(user.getId());
    }

    // select element to database
    @Override
    public UserDto findUserById(Integer id) {
        User user = usersMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND
                        ,String.format("User with %d id not found",id)));
        return userMapStruct.userToUserDto(user);
    }

    // delete element to database
    @Override
    public Integer deleteUserById(Integer id) {
    //        boolean isFound =usersMapper.existsById(id);
    //        System.out.println(isFound);
    //        return id;
        boolean isFound =usersMapper.existsById(id);
        if(isFound){
            // delete by id
            usersMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                ,String.format("User with %d id not found",id));
    }
    @Override
    public Integer updateIsDeleteStatus(Integer id, boolean status) {
        boolean isExists = usersMapper.existsById(id);
        if (isExists){
            usersMapper.updatedIsDeleteById(id,status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                ,String.format("User with %d id not found",id));
    }
    @Override
    public PageInfo<UserDto> findAllUsers(int page, int limit) {
        PageInfo<User> userPageInfo=PageHelper.startPage(page,limit)
                .doSelectPageInfo(usersMapper::select);
        return userMapStruct.userPageInfoTouserDtoPageInfo(userPageInfo);
    }

    @Override
    public UserDto updateUserById(Integer id, UpdateUserDto updateUserDTo) {
        if(usersMapper.existsById(id)){
            User user =userMapStruct.updateUserDtoToUser(updateUserDTo);
            usersMapper.updateById(user);
            return this.findUserById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d id not found",id));
    }

}
