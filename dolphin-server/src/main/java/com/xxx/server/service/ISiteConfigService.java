package com.xxx.server.service;

import com.xxx.server.pojo.SiteConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-30
 */
public interface ISiteConfigService extends IService<SiteConfig> {

    Map<String,Object> getList();
}
