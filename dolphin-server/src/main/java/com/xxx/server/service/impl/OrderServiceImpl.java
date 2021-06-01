package com.xxx.server.service.impl;

import com.xxx.server.pojo.Order;
import com.xxx.server.mapper.OrderMapper;
import com.xxx.server.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private  OrderMapper orderMapper;
    @Override
    public List<Order> getList(Map params) {
        return orderMapper.getList(params);
    }

    @Override
    public void create(Order order) {
        if(order.getPrice() !=null){
            order.setStatus(1);
            order.setTakeDate(LocalDateTime.now());
        }else{
            order.setStatus(0);
        }
        orderMapper.insert(order);
    }

    @Override
    public void edit(Order order) {

        if(order.getPrice() !=null){
            order.setStatus(1);
            order.setTakeDate(LocalDateTime.now());
        }else{
            order.setStatus(0);
        }
        orderMapper.updateById(order);

    }
}
