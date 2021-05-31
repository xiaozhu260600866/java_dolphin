package com.xxx.server.service.impl;

import com.xxx.server.pojo.Category;
import com.xxx.server.mapper.CategoryMapper;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-31
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public RespBean updateClass(Category category) {
        categoryMapper.updateById(category);
        return RespBean.success("添加成功");
    }

    @Override
    public RespBean addClass(Category category) {
        categoryMapper.insert(category);
        return  RespBean.success("修改成功");
    }
}
