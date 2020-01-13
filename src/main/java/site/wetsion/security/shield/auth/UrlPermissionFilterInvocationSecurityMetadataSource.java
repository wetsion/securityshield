package site.wetsion.security.shield.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import site.wetsion.security.shield.domain.SysPermission;
import site.wetsion.security.shield.domain.SysRole;
import site.wetsion.security.shield.service.SysRolePermissionService;
import site.wetsion.security.shield.utils.SecurityConstants;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * Created by wetsion on 2020/1/11.
 */
@Component
public class UrlPermissionFilterInvocationSecurityMetadataSource
        implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String url = filterInvocation.getRequestUrl();
        for (String pu : SecurityConstants.PERMIT_URL) {
            // 检查是否是 需要忽略的URL
            if (pu.equals(url)) {
                return null;
            }
        }
        List<SysPermission> allPermissions =  sysRolePermissionService.findAllPermissions();
        for (SysPermission permission : allPermissions) {
            if (permission.getUrl().equals(url)) {
                List<SysRole> permissionRoles = sysRolePermissionService.findRoleByPermission(permission.getId());
                List<String> permissionRoleNames = permissionRoles.stream().map(
                        new Function<SysRole, String>() {
                    @Override
                    public String apply(SysRole sysRole) {
                        return sysRole.getRoleName();
                    }
                }).collect(Collectors.toList());
                return SecurityConfig.createList(permissionRoleNames.toArray(new String[0]));
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
