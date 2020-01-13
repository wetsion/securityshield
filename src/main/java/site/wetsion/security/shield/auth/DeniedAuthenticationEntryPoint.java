package site.wetsion.security.shield.auth;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理 <br/>
 * 主要针对匿名用户或者未被认证的用户
 *
 * @author weixin
 * @version 1.0
 * @date 2020/1/13 5:30 PM
 */
@Component
@ConditionalOnProperty(value = "security.rest", havingValue = "false")
public class DeniedAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendRedirect("/403");
    }
}
