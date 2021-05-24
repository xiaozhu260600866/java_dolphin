package com.xxx.server.mapper;

import com.xxx.server.pojo.ArticleClass;

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
public interface ArticleClassMapper extends BaseMapper<ArticleClass> {

    List<ArticleClass> getAllWithChildren(int parentId);
}
