package com.xxx.server.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.server.mapper.OrderMapper;
import com.xxx.server.pojo.Article;
import com.xxx.server.pojo.Order;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.IOrderService;
import com.xxx.server.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @ApiOperation("订单列表")
    @GetMapping("/lists")
    public Map Lists(Order order){

        PageHelper.startPage(1, 15, true);
        List<Order> list = orderService.getList(order);
        PageInfo<Order> pageInfo = new PageInfo<Order>(list);
        Map lists = RespBean.getLists(pageInfo, list);
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

}
