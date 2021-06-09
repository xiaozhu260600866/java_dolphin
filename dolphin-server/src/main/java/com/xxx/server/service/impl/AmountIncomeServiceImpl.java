package com.xxx.server.service.impl;

import com.xxx.server.pojo.AmountIncome;
import com.xxx.server.mapper.AmountIncomeMapper;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.IAmountIncomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-06-09
 */
@Service
public class AmountIncomeServiceImpl extends ServiceImpl<AmountIncomeMapper, AmountIncome> implements IAmountIncomeService {
    @Autowired
    private  AmountIncomeMapper amountIncomeMapper;
    @Override
    public List<AmountIncome> getLists(Map params) {
        return amountIncomeMapper.getLists(params);
    }

    @Override
    public RespBean create(AmountIncome income) {
        amountIncomeMapper.insert(income);
        return RespBean.success("添加成功");
    }
}
