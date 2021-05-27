package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_roles")
@ApiModel(value="Role对象", description="")
public class Role extends Base implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "角色名称不能为空")
    private String name;

    @TableField(exist = false)
    private  String[] role_menus;

    @NotBlank(message = "角色英文不能为空")
    @TableField("name_zh")
    private String name_zh;

    @TableField("menu_str")
    private  String menu_str;







}
