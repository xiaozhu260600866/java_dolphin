package com.xxx.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.server.mapper.OrderMapper;
import com.xxx.server.pojo.Poster;
import com.xxx.server.service.IPosterService;
import com.xxx.server.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeControoler {
    @Autowired
    private IPosterService posterService;
    @Autowired
    private OrderMapper orderMapper;
    @ApiOperation("首页")
    @GetMapping("index")
    public Map getIndex(@RequestParam(required = false) Map params){
        List<Poster> silders = posterService.list(new QueryWrapper<Poster>().eq("type", 1));
        List<Poster> location = posterService.list(new QueryWrapper<Poster>().eq("type", 2));
        List<Poster> lists = posterService.list(new QueryWrapper<Poster>().eq("type", 3));
        params.put("silders",silders);
        params.put("location",location);
        params.put("lists",lists);
        return params;
    }

    @ApiOperation("会员首页")
    @GetMapping("user")
    public Map getUser(@RequestParam(required = false) Map params){
        if(Utils.getUser() !=null){
            params.put("userId", Utils.getUser().getId());
            params.put("status", 0);
            params.put("status0Count", orderMapper.selectCount(params));

            params.put("status", 1);
            params.put("status1Count", orderMapper.selectCount(params));

            params.put("status", 2);
            params.put("status2Count", orderMapper.selectCount(params));

            params.put("user", Utils.getUser());

        }else{
            params.put("user", null);
            params.put("status0Count", null);
            params.put("status1Count", null);
            params.put("status2Count", null);
        }
        return params;
    }

}
