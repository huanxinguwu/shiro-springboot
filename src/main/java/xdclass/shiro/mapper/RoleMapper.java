package xdclass.shiro.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import xdclass.shiro.entity.Role;

import java.util.List;

@Mapper
public interface RoleMapper {
    /**
     * 根据用户id查询用户角色
     */

    @Select("<script> select  *  from role WHERE id IN " +
            "<foreach  item='item' index='index' collection='roleIds' open='(' separator=',' close=')'> #{item}   " +
            " </foreach>  </script>")
    List<Role> findRolesByUserId(@Param("roleIds")List<Integer> roleIds);
    /**
     * 通过userid查找roleid
     */
    @Select("select role_id from user_role where user_id=#{userId}")
    List<Integer> findRoleByUserid(@Param("userId")int userId);

}
