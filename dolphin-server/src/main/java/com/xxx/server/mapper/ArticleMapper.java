package com.xxx.server.mapper;

import com.xxx.server.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-24
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> getLists(Article article);
}
