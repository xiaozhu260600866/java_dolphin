package com.xxx.server.controller;


import com.xxx.server.pojo.SiteConfig;
import com.xxx.server.service.ISiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-30
 */
@RestController
@RequestMapping("/system")
public class SiteConfigController {
    @Autowired
    private ISiteConfigService siteConfigService;
    @GetMapping("/config")
    public Map config(){

        Map<String, Object> result = new HashMap<>();
        result.put("detail",siteConfigService.getList());
        return result;
    }

}
