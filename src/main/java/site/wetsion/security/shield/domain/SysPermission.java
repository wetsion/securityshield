package site.wetsion.security.shield.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限
 *
 * @author weixin
 * @version 1.0
 * @CLassName SysPermission
 * @date 2020/1/10 8:09 PM
 */
@Data
@TableName("sys_permission")
public class SysPermission implements Serializable {

    @TableId
    private Long id;

    private String url;
}
