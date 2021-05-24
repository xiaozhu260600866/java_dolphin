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
public class Shop implements Serializable {

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "created_at",fill = FieldFill.INSERT)//创建注解
    private LocalDateTime created_at;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(value = "updated_at",fill = FieldFill.INSERT_UPDATE)//更新注解
    private LocalDateTime updated_at;


}
