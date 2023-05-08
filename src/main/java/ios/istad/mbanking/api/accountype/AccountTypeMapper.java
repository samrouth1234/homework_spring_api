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

    // @Select("select*from account_types") select all
    @SelectProvider(type = AccountTypeProvider.class,method = "buildSelectSql" )
    List<AccountType>select();
    // select by id
    @SelectProvider(type = AccountTypeProvider.class,method = "buildSelectByIdSql")
    Optional<AccountType> selectById (@Param("id") Integer id);

    // create Account
    @InsertProvider(type = AccountTypeProvider.class,method = "buildInsertSql")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void insert (@Param("u") AccountType accountType);

    // update
    @UpdateProvider(type = AccountTypeProvider.class,method = "buildUpdateSql")
    void update(@Param("u") AccountType accountType);

    @Select("SELECT Exists (SELECT * from account_types WHERE id=#{id})")
    boolean existsById(@Param("id")Integer id);

    @DeleteProvider(type = AccountTypeProvider.class,method = "buildDeleteIsByIdSql")
    void deleteById(@Param("u")Integer id);
}


