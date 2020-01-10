package site.wetsion.security.shield.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ShieldSecurityInterceptor
 * @date 2020/1/10 6:20 PM
 */
public class ShieldSecurityInterceptor extends AbstractSecurityInterceptor {



    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }
}
