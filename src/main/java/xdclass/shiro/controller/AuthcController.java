package xdclass.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authc")
public class AuthcController {

    @GetMapping("/showInfo")
    public String showInfo(){
        return "您需要登录才能查看信息";
    }


}
