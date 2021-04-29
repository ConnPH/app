package vip.ph.vueapp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import vip.ph.vueapp.model.EmailLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Just be alive
 * @since 2021-04-29
 */
public interface EmailLoginMapper extends BaseMapper<EmailLogin> {


    @Insert("INSERT INTO t_email_login(email,salt,activetion_time,is_active,password,token) " +
            "VALUES (#{email},#{salt},#{activetionTime},#{isActive},#{password},#{token})")
    int  insertEmailLogin(EmailLogin emailLogin);


    @Select("SELECT email,activetion_time FROM t_email_login WHERE token = #{token} ")
    EmailLogin selectEmailLoginByToken(String token);


    @Update("UPDATE t_email_login SET is_active = 1 where token = #{token}")
    int update(String token);


    @Select("select email,password,salt from t_email_login where email=#{email} and is_active=1")
    List<EmailLogin> selectEmail(String email);
}
