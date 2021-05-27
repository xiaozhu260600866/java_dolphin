package com.xxx.server.service;

import com.xxx.server.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-30
 */
public interface IOrderService extends IService<Order> {

    List<Order> getList(Order order);

    void create(Order order);

    void edit(Order order);
}
