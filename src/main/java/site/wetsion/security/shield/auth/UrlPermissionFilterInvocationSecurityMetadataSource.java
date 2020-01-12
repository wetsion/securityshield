package site.wetsion.security.shield.auth;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import site.wetsion.security.shield.utils.SecurityConstants;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * Created by wetsion on 2020/1/11.
 */
@Component
public class UrlPermissionFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
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
