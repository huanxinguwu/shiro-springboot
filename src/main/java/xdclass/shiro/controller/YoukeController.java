package xdclass.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("")
public class YoukeController {

    @GetMapping("/all")
    public String all(){
        return "所有人都可以看";
    }
}
