package com.xxx.server.mapper;

import com.xxx.server.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-24
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> getLists(Map params);
}
