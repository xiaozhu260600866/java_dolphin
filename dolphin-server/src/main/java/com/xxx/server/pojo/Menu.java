package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_menus")
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String url;
    private String component;
    @TableField("parent_id")
    private Integer parentId;
    private Boolean enabled;

    @ApiModelProperty("子菜单")
    @TableField(exist = false)
    private List<Menu> children;

    @ApiModelProperty("角色列表")
    @TableField(exist = false)
    private  List<Role> roles;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(value = "created_at",fill = FieldFill.INSERT)//创建注解
    private LocalDateTime created_at;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(value = "updated_at",fill = FieldFill.INSERT_UPDATE)//更新注解
    private LocalDateTime updated_at;


}
