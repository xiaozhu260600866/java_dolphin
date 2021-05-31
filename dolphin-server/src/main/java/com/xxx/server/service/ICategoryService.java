package com.xxx.server.service;

import com.xxx.server.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-31
 */
public interface ICategoryService extends IService<Category> {

    RespBean updateClass(Category category);

    RespBean addClass(Category category);
}
