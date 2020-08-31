package xdclass.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xdclass.shiro.entity.Role;
import xdclass.shiro.entity.User;
import xdclass.shiro.mapper.RoleMapper;
import xdclass.shiro.mapper.UserMapper;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @GetMapping("/findUserByUserName")
    public User findUserByUserName(@RequestParam("name")String name){
        User user= userMapper.findUserByUserName(name);
        List<Integer> roleIdlist=roleMapper.findRoleByUserid(user.getId());
        List<Role> roleList=roleMapper.findRolesByUserId(roleIdlist);
        user.setRoleList(roleList);
        return user;
    }

    @GetMapping("/needlogin")
    public String needlogin(){
        return "您需要登录，输入账号密码";
    }

    @GetMapping("/nopermission")
    public String nopermission(){
        return "您没有权限";
    }

    @GetMapping("/index")
    public String index(){
        return "到首页了";
    }

    @GetMapping("/login")
    public String login(String userName, String password){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
        subject.login(token);


        return "登录成功";
    }










}
