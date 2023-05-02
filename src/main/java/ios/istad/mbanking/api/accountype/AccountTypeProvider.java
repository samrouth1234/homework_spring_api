package ios.istad.mbanking.api.accountype;

import org.apache.ibatis.jdbc.SQL;

import java.awt.*;

public class AccountTypeProvider {
    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*");
            FROM("account_types");
        }}.toString();
    }
    public String buildSelectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM("account_types");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String buildInsertSql(){
        return new  SQL(){{
            INSERT_INTO("account_types");
            VALUES("id","#{u.id}");
            VALUES("name","#{u.name}");
        }}.toString();
    }
}
