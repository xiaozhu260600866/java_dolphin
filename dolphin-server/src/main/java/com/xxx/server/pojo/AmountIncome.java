package com.xxx.server.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubin
 * @since 2021-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_amount_income")
@ApiModel(value="AmountIncome对象", description="")
public class AmountIncome extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "user_id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "0代表未审核，1代表审核通过,2代表审核不通过")
    private String status;

    @ApiModelProperty(value = "0是收入，1是支出")
    private Integer type;

    @ApiModelProperty(value = "关联订单表")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "来源")
    private String source;

    private BigDecimal amount;

    @TableField("status_content")
    private String statusContent;

    @TableField("blank_name")
    private String blankName;

    @TableField("blank_cardno")
    private String blankCardno;

    private Integer ratio;

    private String blank;

    @TableField("pay_method")
    private Integer payMethod;

    @TableField("shop_id")
    private Integer shopId;
    private Integer createor;

    @TableField(exist = false)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private  String payMethodString;

    @TableField(exist = false)
    private  UserInfo user;

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
