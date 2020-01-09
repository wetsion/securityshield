package site.wetsion.security.shield.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import site.wetsion.security.shield.domain.SysUser;
import site.wetsion.security.shield.service.SysUserService;

/**
 * @author weixin
 * @version 1.0
 * @CLassName JdbcUserDetailsService
 * @date 2020/1/9 2:59 PM
 */
@Component
public class JdbcUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.getFullSysUserByName(username);
        return SysUserDetails.build(user);
    }
}
