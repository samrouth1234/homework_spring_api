package ios.istad.mbanking.api.user;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Value;

public class UserProvider {
    private  final String tebleName="users";
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tebleName);
            VALUES("name","#{u.name}");
            VALUES("gender","#{u.gender}");
            VALUES("one_signal_id","#{u.oneSignalId}");
            VALUES("student_card_id","#{u.studentCardId}");
            VALUES("is_student","#{u.isStudent}");
            VALUES("is_deleted","FALSE");
        }}.toString();
    }
    public String buildSelectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tebleName);
            WHERE("id = #{id}","is_deleted = TRUE ");
        }}.toString();
    }

    public String buildUpdateIsDeleteByIdSql(){
        return new SQL(){{
            UPDATE(tebleName);
            SET("is_deleted = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String buildDeleteById(){
        return new SQL(){{
            DELETE_FROM(tebleName);
            WHERE("id=#{id}");
        }}.toString();
    }
    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tebleName);
            WHERE("name ilike '%' || #{name} ||'%' ","is_deleted = FALSE ");
            ORDER_BY("id DESC");
        }}.toString();
    }
    public String buildUpdateIsByIdSql(){
        return new SQL(){{
            UPDATE(tebleName);
            SET("name = #{u.name}");
            SET("gender = #{u.gender}");
            WHERE("id = #{u.id}");
        }}.toString();
    }

    public String buildSelectByStudentCardIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tebleName);
            WHERE("student_card_id = #{studentCardId}", "is_deleted = FALSE");
        }}.toString();
    }
}
