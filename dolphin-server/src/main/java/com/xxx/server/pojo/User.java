package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
@TableName("t_users")
@ApiModel(value="Admin对象", description="")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer role;
    @Getter(AccessLevel.NONE)
    private Boolean enabled;
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    private BigDecimal amount;

    @ApiModelProperty("角色")
    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "created_at",fill = FieldFill.INSERT)//创建注解
    private LocalDateTime created_at;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "updated_at",fill = FieldFill.INSERT_UPDATE)//更新注解
    private LocalDateTime updated_at;







    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles !=null && roles.size() > 0){
            List<SimpleGrantedAuthority> authorities = roles.
                stream().
                map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
            return authorities;
        }else{
            return null;
        }

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (enabled != null) {
            return enabled;
        } else {
            return false;
        }
    }
}
