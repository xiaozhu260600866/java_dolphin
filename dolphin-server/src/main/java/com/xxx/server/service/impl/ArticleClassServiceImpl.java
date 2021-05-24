package com.xxx.server.service.impl;

import com.xxx.server.mapper.ArticleClassMapper;
import com.xxx.server.pojo.ArticleClass;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.IArticleClassService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-24
 */
@Service
public class ArticleClassServiceImpl extends ServiceImpl<ArticleClassMapper, ArticleClass> implements IArticleClassService {

    @Autowired
    private  ArticleClassMapper articleClassMapper;
    @Override
    public List<ArticleClass> getAllWithChildren(int parentId) {
        return articleClassMapper.getAllWithChildren(parentId);
    }

    @Override
    public RespBean addClass(ArticleClass articleClass) {
        articleClassMapper.insert(articleClass);
        return RespBean.success("添加成功");
    }

    @Override
    public RespBean updateClass(ArticleClass articleClass) {
        articleClassMapper.updateById(articleClass);
        return RespBean.success("更新成功");
    }
}
