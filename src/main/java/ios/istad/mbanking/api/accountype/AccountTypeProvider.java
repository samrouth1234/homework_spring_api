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
            VALUES("name","#{u.name}");
        }}.toString();
    }
    public String buildUpdateSql(){
        return new SQL(){{
            UPDATE("account_types");
            SET("name=#{u.name}");
            WHERE("id=#{u.id}");
        }}.toString();
    }
    public String buildDeleteIsByIdSql(){
        return new SQL(){{
            DELETE_FROM("account_types");
            WHERE("id=#{u.id}");
        }}.toString();
    }
}
