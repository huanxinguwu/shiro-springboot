package xdclass.shiro.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xdclass.shiro.entity.Permission;

import java.util.List;

@Mapper
public interface PermissionMapper {
    /**
     * 通过角色查找权限
     */
    @Select("select p.id as id,p.name as name ,p.url as url from role_permission rp left join permission p on rp.permission_id=p.id where rp.role_id=#{roleId}")

    List<Permission> findPermissionListByRoleId(@Param("roleId") int roleId);
}
