package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_articles")
@ApiModel(value="Articles对象", description="")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "新闻标题")
    private String title;

    @ApiModelProperty(value = "父类id")
    private Integer fclass;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "remark")
    private String remark;

    @ApiModelProperty(value = "发表日期")
    @TableField("published_at")
    private LocalDate publishedAt;

    @ApiModelProperty(value = "cover")
    private String cover;

    @ApiModelProperty(value = "父类")
    @TableField(exist = false)
    private  ArticleClass articleClass;


}
