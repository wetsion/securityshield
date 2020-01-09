package site.wetsion.security.shield.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.wetsion.security.shield.domain.SysRole;
import site.wetsion.security.shield.domain.SysUser;
import site.wetsion.security.shield.mapper.SysRoleMapper;
import site.wetsion.security.shield.mapper.SysUserMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SysUserService
 * @date 2020/1/9 3:43 PM
 */
@Service
@Transactional
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {


    @Autowired
    SysRoleMapper sysRoleMapper;

    /**
     * 获取完整的user对象，包含角色列表
     *
     * @param name
     * @author weixin
     * @date 4:05 PM 2020/1/9
     * @return site.wetsion.security.shield.domain.SysUser
     **/
    public SysUser getFullSysUserByName(String name) {
        SysUser user = baseMapper.selectOne(
                new QueryWrapper<SysUser>().eq("username", name));
        Set<SysRole> roleSet = sysRoleMapper.getAllRolesByUserId(user.getId());
        Set<String> roles = new HashSet<>();
        roleSet.forEach(role -> {
            roles.add(role.getRoleName());
        });
        user.setRoles(roles);
        return user;
    }
}
