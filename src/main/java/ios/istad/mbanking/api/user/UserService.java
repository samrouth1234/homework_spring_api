package ios.istad.mbanking.api.user;
import com.github.pagehelper.PageInfo;
import ios.istad.mbanking.api.user.webs.CreateUserDto;
import ios.istad.mbanking.api.user.webs.UpdateUserDto;
import ios.istad.mbanking.api.user.webs.UserDto;

public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);

    UserDto findUserById(Integer id);

    PageInfo<UserDto>findAllUsers(int page,int limit,String name);

    Integer deleteUserById(Integer id);

    Integer updateIsDeleteStatus(Integer id ,boolean status);

    UserDto updateUserById(Integer id, UpdateUserDto updateUserDTo);

    UserDto findUserByStudentCardId(String studentCardId);

}
