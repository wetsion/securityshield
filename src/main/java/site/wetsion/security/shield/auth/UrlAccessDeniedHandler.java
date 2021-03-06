package site.wetsion.security.shield.auth;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * url访问失败处理 <br/>
 * 针对认证过的用户访问无权限时的异常
 *
 * @author weixin
 * @version 1.0
 * @CLassName UrlAccessDeniedHandler
 * @date 2020/1/10 7:50 PM
 */
@Component
@ConditionalOnProperty(value = "security.rest", havingValue = "false")
public class UrlAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendRedirect("/403");
    }
}
