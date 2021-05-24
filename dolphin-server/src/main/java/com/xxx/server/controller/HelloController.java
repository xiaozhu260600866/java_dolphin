package com.xxx.server.controller;
import java.time.LocalDateTime;


import com.xxx.server.pojo.Role;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Validated
public class HelloController {
    @GetMapping("/employee/basic/hello")
    public String authText(){
        return "employee/basic/hello";
    }
    @GetMapping("/employee/advanced/hello")
    public String authTex2(){
        return "employee/advanced/hello";
    }

    @GetMapping("text")
    public Role  text(){
        Role role = new Role();
        return role;
    }

}
