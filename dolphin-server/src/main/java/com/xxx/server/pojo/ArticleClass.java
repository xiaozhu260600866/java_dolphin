package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_article_class")
@ApiModel(value="ArticleClass对象", description="")
public class ArticleClass extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "类别名称不能为空")
    @ApiModelProperty(value = "类别姓名")
    private String name;

    @ApiModelProperty(value = "类别排序")
    private Integer sort;

    @ApiModelProperty(value = "类别id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父id")
    private Integer fid;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "图片")
    private String cover;

    @TableField(exist = false)
    @ApiModelProperty("子类别")
    private List<ArticleClass> children;







}
