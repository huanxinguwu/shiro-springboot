package xdclass.shiro.config;

import org.apache.commons.collections.ArrayStack;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import xdclass.shiro.entity.Permission;
import xdclass.shiro.entity.Role;
import xdclass.shiro.entity.User;
import xdclass.shiro.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义realm
 */
public class customRealm extends AuthorizingRealm {
@Autowired
private UserMapper userMapper;
    @Override
    //用户授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权开始");
        String userName=(String)principalCollection.getPrimaryPrincipal();
        User user=userMapper.findUserByUserName(userName);
        List<String> stringRoleList=new ArrayList<>();
        List<String> stringpermissionList=new ArrayList<>();
        //从user中获取RoleList
        List<Role> roleList=user.getRoleList();
        for (Role role:roleList){
            stringRoleList.add(role.getName());
            List<Permission> permissionList=role.getPermissionList();
            for (Permission p:permissionList){
                if (p!=null){
                    stringpermissionList.add(p.getName());
                }
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRoles(stringRoleList);
        info.addStringPermissions(stringpermissionList);

        return info;
    }

    @Override
    //用户认证，登录的时候回调用
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("用户认证");
        //获取用户输入的用户名
        String userName=(String)authenticationToken.getPrincipal();
       //从数据库获取用户信息
        User user=userMapper.findUserByUserName(userName);
        if (user==null){
            throw new RuntimeException("用户不存在");
        }
        //获取密码
        String password=user.getPassword();
        //校验密码
        if (password==null || "".equals(password)){
            return null;
        }
        return new SimpleAuthenticationInfo(userName,user.getPassword(),this.getClass().getName());
    }
}
