package com.xxx.server.service.impl;

import com.xxx.server.mapper.ShopMapper;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Shop;
import com.xxx.server.service.IShopService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public RespBean create(Shop shop) {
        shopMapper.insert(shop);
        return RespBean.success("添加成功");
    }

    @Override
    public RespBean edit(Shop shop) {
        shopMapper.updateById(shop);
        return RespBean.success("修改成功");
    }

    @Override
    public RespBean del(Shop shop) {
        shopMapper.deleteById(shop.getId());
        return RespBean.success("删除成功");
    }
}
