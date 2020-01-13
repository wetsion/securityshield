package site.wetsion.security.shield.auth;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * rest url 认证失败处理 <br/>
 * 主要针对匿名用户或者未被认证的用户
 *
 * @author weixin
 * @version 1.0
 * @date 2020/1/13 4:57 PM
 */
@Component
@ConditionalOnProperty(value = "security.rest", havingValue = "true")
public class RestDeniedAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject result = new JSONObject();
        result.put("status", "error");
        result.put("code", 403);
        result.put("message", "无权限访问！");
        out.write(result.toJSONString());
        out.flush();
        out.close();
    }
}
