package site.wetsion.security.shield.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ShieldJdbcAuthenticationManager
 * @date 2020/1/15 3:51 PM
 */
//@Component
public class ShieldJdbcAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
