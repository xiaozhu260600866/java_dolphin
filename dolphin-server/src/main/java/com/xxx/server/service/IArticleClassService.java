package com.xxx.server.service;

import com.xxx.server.pojo.ArticleClass;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-24
 */
public interface IArticleClassService extends IService<ArticleClass> {

    List<ArticleClass> getAllWithChildren(int parentId);

    RespBean addClass(ArticleClass articleClass);

    RespBean updateClass(ArticleClass articleClass);
}
