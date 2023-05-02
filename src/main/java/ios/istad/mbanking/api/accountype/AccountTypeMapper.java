package ios.istad.mbanking.api.accountype;

import ios.istad.mbanking.api.user.User;
import ios.istad.mbanking.api.user.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface AccountTypeMapper {

    @Select("Select Exists (select * from account_types where id=#{id})")
    boolean existsById(@Param("id")Integer id);
    @InsertProvider(type = AccountTypeProvider.class,method = "buildInsertSql")
    void insert (@Param("u") AccountType accountType);

    // @Select("select*from account_types")
    @SelectProvider(type = AccountTypeProvider.class,method = "buildSelectSql" )
    List<AccountType>select();

    @SelectProvider(type = AccountTypeProvider.class,method = "buildSelectByIdSql")
    // used ban te select
    @Results(id = "accountResultMap",value = {
            @Result(column = "id",property ="idAccount" ),
            @Result(column = "name",property = "nameAccount")
    })
    Optional<AccountType> selectById (@Param("id") Integer id);

}
