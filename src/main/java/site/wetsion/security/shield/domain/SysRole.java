package site.wetsion.security.shield.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SysRole
 * @date 2020/1/9 3:37 PM
 */
@Data
@TableName("sys_role")
public class SysRole implements Serializable {

    @TableId
    private Long id;

    private String roleName;
}
