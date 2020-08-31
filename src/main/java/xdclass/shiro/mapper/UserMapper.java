package xdclass.shiro.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xdclass.shiro.entity.User;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查用户信息
     */
    @Select("select * from user where user_name=#{userName}")
    User findUserByUserName(@Param("userName")String userName );

    /**
     * 根据id查询用户
     */
    @Select("select * from user where id=#{id}")
    User findById(@Param("id")int id);

    /**
     * 根据用户名，密码查新用户
     */
    @Select("select * from user where user_name=#{userName} AND password=#{pwd}")
    User findUser(@Param("userName") String userName,@Param("pwd") String pwd);
}
