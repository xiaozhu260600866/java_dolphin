package com.xxx.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.server.annotation.MultiRequestBody;
import com.xxx.server.mapper.ArticleClassMapper;
import com.xxx.server.pojo.Article;
import com.xxx.server.pojo.ArticleClass;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Role;
import com.xxx.server.service.IArticleClassService;
import com.xxx.server.service.IArticleService;
import com.xxx.server.service.impl.ArticleServiceImpl;
import com.xxx.server.utils.Utils;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {
    @Autowired
    private IArticleClassService articleClassService;
    @Autowired
    private ArticleClassMapper articleClassMapper;

    @Autowired
    private IArticleService articleService;


    @ApiOperation("添加类别")
    @GetMapping("/class")
    public Map getArticleClass(){
        List<ArticleClass> list = articleClassMapper.selectList(new QueryWrapper<ArticleClass>().orderByDesc("id"));
        Map<String,Object> result = new HashMap<>();
        result.put("lists",list);
       return result;
    }
    @PostMapping("/class")
    public RespBean postArticleClass(@Valid @MultiRequestBody ArticleClass articleClass){
        if(articleClass.getId() !=null){
            return articleClassService.updateClass(articleClass);
        }else{
            return articleClassService.addClass(articleClass);
        }
    }
    @ApiOperation("修改类别")
    @PostMapping("/ajaxclass")
    public Map updateArticleClass(@RequestBody Map<String,Object> params){
        String type = (String)params.get("type");
        Integer id = (Integer) params.get("v");
        if(type.equals("del_zc")){
            articleClassService.removeById(id);
        }
        return params;
    }

    @ApiModelProperty("查询新闻列表")
    @GetMapping("/lists")
    public Map getLists(Article article){
        PageHelper.startPage(1, 15, true);
        List<Article> list =  articleService.getLists(article);
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        Map lists = RespBean.getLists(pageInfo, list);
        return  lists;
    }
    @ApiModelProperty("新建新闻")
    @PostMapping("/create")
    public  RespBean create(@MultiRequestBody Article article){
        article.setCover(Utils.getCoverString(article.getCover(),"article"));
        return articleService.create(article);
    }

    @ApiModelProperty("修改新闻")
    @PostMapping("/edit")
    public RespBean edit(@MultiRequestBody Article article){
        System.out.println("edit");
        System.out.println("你好吗");
        article.setCover(Utils.getCoverString(article.getCover(),"article"));
        return articleService.edit(article);
    }

    @ApiModelProperty("删除新闻")
    @PostMapping("/del")
    public RespBean del(@MultiRequestBody Article article){
        return articleService.del(article);
    }


}
