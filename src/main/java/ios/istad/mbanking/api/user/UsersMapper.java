package ios.istad.mbanking.api.user;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UsersMapper {

    // insert element to database
    @InsertProvider(type = UserProvider.class,method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("u") User user);

    // selectProvider byID
    @SelectProvider(type = UserProvider.class,method = "buildSelectByIdSql")
    @Results(id = "userResultMap",value = {
            @Result(column = "student_card_id",property ="studentCardId" ),
            @Result(column = "is_student",property = "isStudent")
    })
    Optional <User> selectById (@Param("id") Integer id);

    @SelectProvider(type = UserProvider.class, method = "buildSelectByStudentCardIdSql")
    @ResultMap("userResultMap")
    Optional<User> selectByStudentCardId(@Param("studentCardId") String studentCardId);

    @SelectProvider (type = UserProvider.class,method = "buildSelectSql")
    @ResultMap("userResultMap")
    List<User>select(@Param("name") String name);

    @Select("Select Exists (select * from users where id=#{id})")
    boolean existsById(@Param("id")Integer id);

    // delete element in database  userBYid
    @DeleteProvider(type = UserProvider.class,method = "buildDeleteById")
    void deleteById(@Param("id")Integer id);

    // delete element in database  user Status

    @DeleteProvider(type = UserProvider.class,method = "buildUpdateIsDeleteByIdSql")
    void updatedIsDeleteById(@Param("id")Integer id, @Param("status") boolean isStatus);

    @UpdateProvider(type = UserProvider.class,method = "buildUpdateIsByIdSql")
    void updateById(@Param("u")User user);


}
