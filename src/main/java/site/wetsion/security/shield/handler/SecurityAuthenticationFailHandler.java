package site.wetsion.security.shield.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import site.wetsion.security.shield.auth.SysUserDetails;
import site.wetsion.security.shield.utils.JwtUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SecurityAuthenticationFailHandler
 * @date 2020/1/15 6:57 PM
 */
public class SecurityAuthenticationFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        JSONObject object = new JSONObject();
        object.put("status", HttpStatus.FORBIDDEN.value());
        object.put("msg", "登录失败");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(object.toJSONString());
    }
}
