package com.xxx.server.mapper;

import com.xxx.server.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-30
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> getList(Order order);
}
