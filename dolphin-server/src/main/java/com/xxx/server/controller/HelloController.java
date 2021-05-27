package com.xxx.server.controller;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


import com.xxx.server.mapper.RoleMapper;
import com.xxx.server.pojo.Role;
import com.xxx.server.service.IRoleService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Validated
public class HelloController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private RoleMapper roleMapper;
    @GetMapping("/employee/basic/hello")
    public String authText(){
        return "employee/basic/hello";
    }
    @GetMapping("/employee/advanced/hello")
    public String authTex2(){
        return "employee/advanced/hello";
    }

    @GetMapping("text")
    public List<Role> text() throws IOException {

        Thumbnails.of("D:\\java\\dolphin\\backServer\\dolphin-server\\target\\classes\\static\\images\\article\\a.jpg").size(400,500).toFile("D:\\java\\dolphin\\backServer\\dolphin-server\\target\\classes\\static\\images\\article\\b.jpg");//变为400*300,遵循原图比例缩或放到400*某个高度
        roleService.removeById(20);
        return roleService.list(null);
    }

}
