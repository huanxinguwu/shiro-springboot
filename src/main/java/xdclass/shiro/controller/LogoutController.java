package xdclass.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    /**
     * 退出功能
     * @return
     */
    @GetMapping("/logout")
    public String logout(){
        Subject subject= SecurityUtils.getSubject();
        if (subject.getPrincipal()!=null){

        }
        SecurityUtils.getSubject().logout();
        return "退出成功";
    }


}
