package com.xxx.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.server.annotation.MultiRequestBody;
import com.xxx.server.mapper.ArticleClassMapper;
import com.xxx.server.mapper.ArticleMapper;
import com.xxx.server.pojo.*;
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

    @Autowired
    private ArticleMapper articleMapper;


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
        articleClass.setCover(Utils.getCoverString(articleClass.getCover(),"product"));
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
        }else if(type.equals("update_class")){
            try {
                //判断该类字段的类型
                String modelFildType = ArticleClass.class.getDeclaredField((String) params.get("t")).getType().getName();
                if(modelFildType.equals("java.lang.Integer")){
                    articleClassService.update(new UpdateWrapper<ArticleClass>().eq("id",id).set((String) params.get("t"),(Integer)params.get("v")));
                }else if(modelFildType.equals("java.lang.String")){
                    articleClassService.update(new UpdateWrapper<ArticleClass>().eq("id",id).set((String) params.get("t"),(String)params.get("v")));
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

    @ApiModelProperty("查询新闻列表")
    @GetMapping("/lists")
    public Map getLists(@RequestParam(required = false) Map params){
        PageHelper.startPage(1, 15, true);
        List<Article> list =  articleService.getLists(params);
        RespBean.startPage(params);
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        Map lists = RespBean.getLists(list);
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

    @ApiOperation("查看新闻")
    @GetMapping("/show")
    public RespBean show(Article article){
        Article detail = articleMapper.selectById(article.getId());
        return RespBean.success("成功",detail);
    }


}
