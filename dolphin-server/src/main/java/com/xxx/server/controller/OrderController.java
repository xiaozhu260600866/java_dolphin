package com.xxx.server.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.server.mapper.OrderMapper;
import com.xxx.server.mapper.UserMapper;
import com.xxx.server.pojo.Article;
import com.xxx.server.pojo.Order;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.User;
import com.xxx.server.service.IOrderService;
import com.xxx.server.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-30
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @ApiOperation("订单列表")
    @GetMapping("/lists")
    public Map Lists(@RequestParam(required = false) Map params){
        RespBean.startPage(params);
        List<Order> list = orderService.getList(params);
        Map lists = RespBean.getLists(list);

        params.put("sumField","price");
        BigDecimal price = orderMapper.selectSum(params);
        Integer count = orderMapper.selectCount(params);

        lists.put("price",price);
        lists.put("count",count);
        return  lists;
    }

    @ApiOperation("订单列表")
    @PostMapping("/upload")
    public RespBean upload(MultipartFile file){
        ImportParams importParams  = new ImportParams();
        //去掉第一行； 从第二行开始
        //importParams.setTitleRows(1);
        try {
            List<Order> list = ExcelImportUtil.importExcel(file.getInputStream(), Order.class, importParams);
            list.forEach(order->{
                Order isEx = orderMapper.selectOne(new QueryWrapper<Order>().eq("order_no", order.getOrderNo()));
                order.setShopId(Utils.getUser().getShopId());
                order.setCreateor(Utils.getUser().getId());
                if(isEx!=null){
                    order.setId(isEx.getId());
                    orderService.edit(order);
                }else{
                    orderService.create(order);
                }


            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return RespBean.success("成功");
    }
    @PostMapping("/pay")
    public RespBean pay(@RequestBody Order order){
        Order orderDetail = orderMapper.selectOne(new QueryWrapper<Order>().eq("id", order.getId()));

        if(order.getPayMethod() == 2){
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", order.getUserId()));
            if(user.getAmount().compareTo(order.getPrice()) == -1){
                    return RespBean.error("余额不足");
            }
        }
        order.setFinishDate(LocalDateTime.now());
        order.setStatus(2);
        orderMapper.updateById(order);
        return RespBean.success("添加成功");
    }
    @PostMapping("/del")
    public  RespBean del(@RequestBody Order order){
         orderService.removeById(order.getId());
         return RespBean.success("操作成功");
    }

}
