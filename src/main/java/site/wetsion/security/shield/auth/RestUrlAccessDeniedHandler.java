package site.wetsion.security.shield.auth;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * rest url请求 访问失败处理 <br/>
 * 针对认证过的用户访问无权限时的异常
 *
 * @author weixin
 * @version 1.0
 * @date 2020/1/13 3:22 PM
 */
@Component
@ConditionalOnProperty(value = "security.rest", havingValue = "true")
public class RestUrlAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
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
