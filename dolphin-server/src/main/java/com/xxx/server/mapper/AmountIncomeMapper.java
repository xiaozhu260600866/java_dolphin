package com.xxx.server.mapper;

import com.xxx.server.pojo.AmountIncome;
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
 * @since 2021-06-09
 */
public interface AmountIncomeMapper extends BaseMapper<AmountIncome> {

    List<AmountIncome> getLists(Map params);
    BigDecimal selectSum(Map params);
    Integer selectCount1(Map params);
}
