package ios.istad.mbanking.api.user;
import com.github.pagehelper.PageInfo;
import ios.istad.mbanking.api.user.webs.CreateUserDto;
import ios.istad.mbanking.api.user.webs.UpdateUserDto;
import ios.istad.mbanking.api.user.webs.UserDto;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User createUserDtoToUser(CreateUserDto createUserDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    User updateUserDtoToUser(UpdateUserDto updateUserDTo);
    PageInfo <UserDto> userPageInfoTouserDtoPageInfo (PageInfo <User> userPageInfo);

}
