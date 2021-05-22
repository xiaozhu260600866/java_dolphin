package com.xxx.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Shop;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-22
 */
public interface IShopService extends IService<Shop> {

    RespBean create(Shop shop);

    RespBean edit(Shop shop);

    RespBean del(Shop shop);
}
