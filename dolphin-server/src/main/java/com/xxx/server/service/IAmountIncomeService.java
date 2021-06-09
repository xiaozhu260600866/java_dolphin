package com.xxx.server.service;

import com.xxx.server.pojo.AmountIncome;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.RespBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-06-09
 */
public interface IAmountIncomeService extends IService<AmountIncome> {

    List<AmountIncome> getLists(Map params);

    RespBean create(AmountIncome income);
}
