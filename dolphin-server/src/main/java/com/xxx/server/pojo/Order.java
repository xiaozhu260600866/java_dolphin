package com.xxx.server.pojo;

import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.xxx.server.mapper.ShopMapper;
import com.xxx.server.service.IShopService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
@ApiModel(value="Order对象", description="")

public class Order extends  Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String date;

    @Excel(name = "会员卡号")
    @TableField("user_id")
    private Integer userId;

    private Integer status;

    private String phone;

    private String remark;
    @Excel(name = "快递单号")
    @TableField("order_no")
    private String orderNo;
    @Excel(name = "重量")
    private Integer num;
    @Excel(name = "货品名称")
    @TableField("goods_name")
    private String goodsName;

    @Excel(name = "重量")
    @ApiModelProperty(value = "重量")
    private String weight;

    @Excel(name = "货品所放位置")
    @ApiModelProperty(value = "商品位置")
    @TableField("goods_position")
    private String goodsPosition;

    @Excel(name = "物流费")
    @ApiModelProperty(value = "金额")
    private BigDecimal price;

    @ApiModelProperty(value = "支付方式")
    @TableField("pay_method")
    private Integer payMethod;

    @TableField("shop_id")
    private Integer shopId;

    @TableField("finish_date")
    private LocalDateTime finishDate;

    @ApiModelProperty(value = "取货日期")
    @TableField("take_date")
    private LocalDateTime takeDate;

    @ApiModelProperty(value = "新建者userId")
    private Integer createor;

    @ApiModelProperty(value = "用户")
    @TableField(exist = false)
    private  UserInfo user;

    @ApiModelProperty(value = "门店")
    @TableField(exist = false)
    private  Shop shop;

    @ApiModelProperty(value = "创建者")
    @TableField(exist = false)
    private  UserInfo staff;


    @TableField(exist = false)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private  String payMethodString;

    public String getPayMethodString() {
        if(payMethod !=null){
            switch(payMethod){
                case 2 :return  "余额支付";
                case 3 :return  "现金支付";
                case 4: return "Mpay支付";
            }
        }

        return "123";
    }






}
