package site.wetsion.security.shield.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-权限 关联
 *
 * @author weixin
 * @version 1.0
 * @CLassName SysRolePermission
 * @date 2020/1/10 8:09 PM
 */
@Data
@TableName("sys_role_permission")
public class SysRolePermission implements Serializable {

    @TableId
    private Long id;

    private Long roleId;

    private Long permissionId;
}
