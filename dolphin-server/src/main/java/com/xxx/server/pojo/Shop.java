package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shops")
@ApiModel(value="Shops对象", description="")
public class Shop extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message="姓名不能为空")
    @ApiModelProperty(value = "联系人")
    private String name;
    @NotBlank(message="电话不能为空")
    @ApiModelProperty(value = "联系人电话")
    private String phone;
    @NotBlank(message="门店名称不能为空")
    @ApiModelProperty(value = "门店名称")
    @TableField("company_name")
    private String companyName;




}
