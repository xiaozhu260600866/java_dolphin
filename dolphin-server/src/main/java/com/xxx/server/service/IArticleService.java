package com.xxx.server.service;

import com.xxx.server.pojo.Article;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.RespBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-24
 */
public interface IArticleService extends IService<Article> {

    List<Article> getLists(Article article);


    RespBean create(Article article);

    RespBean edit(Article article);

    RespBean del(Article article);
}
