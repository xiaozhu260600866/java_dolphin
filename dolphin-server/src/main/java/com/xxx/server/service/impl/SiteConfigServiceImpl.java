package com.xxx.server.service.impl;

import com.xxx.server.pojo.SiteConfig;
import com.xxx.server.mapper.SiteConfigMapper;
import com.xxx.server.service.ISiteConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-30
 */
@Service
public class SiteConfigServiceImpl extends ServiceImpl<SiteConfigMapper, SiteConfig> implements ISiteConfigService {
    @Autowired
    private  SiteConfigMapper siteConfigMapper;
    @Override
    public Map<String, Object> getList() {
        Map<String,Object> result = new HashMap<>();

        List<SiteConfig> list = siteConfigMapper.selectList(null);
        list.forEach(siteConfig -> {
            result.put(siteConfig.getName(),siteConfig.getValue());
        });
        return result;
    }
}
