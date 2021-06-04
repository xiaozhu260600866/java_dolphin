package com.xxx.server.mapper;

import com.xxx.server.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-30
 */
public interface OrderMapper   extends BaseMapper<Order>  {

    List<Order> getList(Map params);
    BigDecimal selectSum(Map params);
    Integer selectCount(Map params);
}
