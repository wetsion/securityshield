package site.wetsion.security.shield.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.wetsion.security.shield.domain.SysUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义 UserDetails
 *
 * @author weixin
 * @version 1.0
 * @CLassName SysUserDetails
 * @date 2020/1/9 3:02 PM
 */
public class SysUserDetails implements UserDetails {

    private SysUser sysUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Set<String> roles = sysUser.getRoles();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
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
        return true;
    }

    private void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    /**
     * 构建 SysUserDetails 对象
     *
     * @param sysUser
     * @author weixin
     * @date 3:26 PM 2020/1/9
     * @return site.wetsion.security.shield.auth.SysUserDetails
     **/
    public static SysUserDetails build(SysUser sysUser) {
        SysUserDetails sysUserDetails = new SysUserDetails();
        sysUserDetails.setSysUser(sysUser);
        return sysUserDetails;
    }
}
