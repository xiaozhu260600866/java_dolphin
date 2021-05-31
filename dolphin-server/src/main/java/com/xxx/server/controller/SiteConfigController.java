package com.xxx.server.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xxx.server.annotation.MultiRequestBody;
import com.xxx.server.mapper.SiteConfigMapper;
import com.xxx.server.pojo.Category;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.SiteConfig;
import com.xxx.server.service.ICategoryService;
import com.xxx.server.service.ISiteConfigService;
import com.xxx.server.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    @Autowired
    private SiteConfigMapper siteConfigMapper;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/config")
    public Map config(){
        Map<String, Object> result = new HashMap<>();
        result.put("detail",siteConfigService.getList());
        result.put("category",categoryService.list());
        return result;
    }

    @PostMapping("/class")
    public RespBean postClass(@MultiRequestBody  Category category){
        category.setCover(Utils.getCoverString(category.getCover(),"product"));
        if(category.getId() !=null){
            return categoryService.updateClass(category);
        }else{
            return categoryService.addClass(category);
        }
    }

    @PostMapping("/ajaxclass")
    public Map updateArticleClass(@RequestBody Map<String,Object> params){
        String type = (String)params.get("type");
        Integer id = (Integer) params.get("id");
        if(type.equals("del_zc")){
            categoryService.removeById(id);
        }else if(type.equals("update_class")){
            try {
                //判断该类字段的类型
                String modelFildType = Category.class.getDeclaredField((String) params.get("t")).getType().getName();
                if(modelFildType.equals("java.lang.Integer")){
                    categoryService.update(new UpdateWrapper<Category>().eq("id",id).set((String) params.get("t"),(Integer)params.get("v")));
                }else if(modelFildType.equals("java.lang.String")){
                    categoryService.update(new UpdateWrapper<Category>().eq("id",id).set((String) params.get("t"),(String)params.get("v")));
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

    @PostMapping("/config")
    public  RespBean postConfig(@RequestBody Map<String,Object> params){
        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            String key = stringObjectEntry.getKey();
            Object value = stringObjectEntry.getValue();
            String insetValue = null ;
            if(value instanceof ArrayList){
                ArrayList value1 = (ArrayList) value;
                if(value1.size() >0){
                    insetValue = Utils.getCoverString(JSON.toJSON(value).toString(), "product");
                }
            }else{
                insetValue = (String) value;
            }
            if(insetValue !=null){
                SiteConfig isEx = siteConfigMapper.selectOne(new QueryWrapper<SiteConfig>().eq("name", key));
                SiteConfig siteConfig = new SiteConfig();
                siteConfig.setName(key);
                siteConfig.setValue(insetValue);
                if(isEx!=null){
                    siteConfig.setId(isEx.getId());
                    siteConfigService.updateById(siteConfig);
                }else{
                    siteConfigService.save(siteConfig);
                }

            }


        }


        return RespBean.success("操作成功");
    }

}
