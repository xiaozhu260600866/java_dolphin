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
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_infos")
@ApiModel(value="UserInfos对象", description="")
public class UserInfo extends  Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关联user表的id")
    @TableField("user_id")
    private Integer userId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "管理员姓名")
    @NotBlank(message = "管理员姓名不能为空")
    private String name;

    @NotBlank(message = "管理员手机不能为空")
    @ApiModelProperty(value = "管理员手机号码")
    private String phone;

    @ApiModelProperty(value = "住宅电话")
    private String telephone;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "用户头像")
    @TableField("user_face")
    private String userFace;


    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty("user表")
    @TableField(exist = false)
    private User user;

    @ApiModelProperty("shop表")
    @TableField(exist = false)
    private  Shop shop;



//    public String getUserFace() {
//        if(userFace !=null){
//            StringBuffer res = new StringBuffer();
//            userFace.forEach(v->{
//                res.append(v.get("name"));
//            });
//            return res.toString();
//        }else{
//            return "";
//        }
//
//    }
}
