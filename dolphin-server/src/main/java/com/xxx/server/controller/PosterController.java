package com.xxx.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.server.annotation.MultiRequestBody;

import com.xxx.server.pojo.Article;
import com.xxx.server.pojo.Poster;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.IPosterService;
import com.xxx.server.utils.Utils;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/poster")
public class PosterController {
    @Autowired
    private IPosterService posterService;


    @ApiOperation("广告列表")
    @GetMapping("/lists")
    public Map getLists(@RequestParam(required = false) Map params) {
        QueryWrapper<Poster> posterQueryWrapper = new QueryWrapper<>();
        if (params.get("name") != null) {
            posterQueryWrapper.like("name", params.get("name"));
        } else if (params.get("type") != null) {
            posterQueryWrapper.eq("type", params.get("type"));
        }
        RespBean.startPage(params);
        List<Poster> list = posterService.list(posterQueryWrapper);
        Map lists = RespBean.getLists(list);
        lists.put("user", Utils.getUser());
        return lists;
    }

    @ApiOperation("新增广告")
    @PostMapping("/create")
    public RespBean create(@MultiRequestBody Poster poster) {
        poster.setPic(Utils.getCoverString(poster.getPic(), "poster"));
        posterService.save(poster);
        return RespBean.success("添加成功");
    }

    @ApiOperation("修改广告")
    @PostMapping("/edit")
    public RespBean edit(@MultiRequestBody Poster poster) {
        poster.setPic(Utils.getCoverString(poster.getPic(), "poster"));
        posterService.updateById(poster);
        return RespBean.success("添加成功");
    }

    @ApiModelProperty("删除广告")
    @PostMapping("/del")
    public RespBean del(@MultiRequestBody Poster poster) {
        posterService.removeById(poster.getId());
        return RespBean.success("删除成功");
    }

}
