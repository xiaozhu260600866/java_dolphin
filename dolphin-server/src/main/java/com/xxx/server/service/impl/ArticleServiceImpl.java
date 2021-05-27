package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.server.mapper.ArticleMapper;
import com.xxx.server.pojo.Article;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.IArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    private  ArticleMapper articleMapper;

    @Override
    public List<Article> getLists(Article article) {
        return articleMapper.getLists(article);
    }



    @Override
    public RespBean create(Article article) {
        articleMapper.insert(article);
        return RespBean.success("添加成功");
    }

    @Override
    public RespBean edit(Article article) {
        articleMapper.updateById(article);
        return RespBean.success("修改成功");
    }

    @Override
    public RespBean del(Article article) {
        articleMapper.deleteById(article.getId());
        return RespBean.success("删除成功");
    }
}
