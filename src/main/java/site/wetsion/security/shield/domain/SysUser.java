package site.wetsion.security.shield.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SysUser
 * @date 2020/1/9 2:55 PM
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    @TableId
    private Long id;

    private String username;

    private String password;

    @TableField(exist = false)
    private Set<String> roles;
}
