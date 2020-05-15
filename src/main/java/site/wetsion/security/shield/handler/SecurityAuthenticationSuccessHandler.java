package site.wetsion.security.shield.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import site.wetsion.security.shield.auth.SysUserDetails;
import site.wetsion.security.shield.utils.JwtUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SecurityAuthenticationSuccessHandler
 * @date 2020/1/15 6:55 PM
 */
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        if (authentication != null) {
            SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
            String token = JwtUtil.generateToken(sysUserDetails);
            JSONObject object = new JSONObject();
            object.put("status", HttpStatus.OK.value());
            object.put("msg", "登录成功");
            object.put("data", "Bearer " + token);
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Authorization", "Bearer " + token);
            response.getWriter().write(object.toJSONString());
        }
    }
}
