package site.wetsion.security.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.wetsion.security.shield.domain.SysPermission;
import site.wetsion.security.shield.domain.SysRole;
import site.wetsion.security.shield.mapper.SysPermissionMapper;
import site.wetsion.security.shield.mapper.SysRoleMapper;
import site.wetsion.security.shield.mapper.SysRolePermissionMapper;

import java.util.List;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SysRolePermissionService
 * @date 2020/1/13 11:45 AM
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRolePermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 获取所有权限
     *
     * @author weixin
     * @date 11:47 AM 2020/1/13
     * @return java.util.List<site.wetsion.security.shield.domain.SysPermission>
     **/
    public List<SysPermission> findAllPermissions() {
        return sysPermissionMapper.selectList(null);
    }

    public List<SysRole> findRoleByPermission(Long permissionId) {
        return sysRoleMapper.findRoleByPermission(permissionId);
    }
}
