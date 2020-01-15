package site.wetsion.security.shield.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import site.wetsion.security.shield.utils.JwtUtil;
import site.wetsion.security.shield.utils.SecurityConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截请求，jwt 鉴权 的filter
 *
 * @author weixin
 * @version 1.0
 * @CLassName JwtFilter
 * @date 2020/1/13 6:55 PM
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private UserDetailsService shieldJdbcUserDetailsServiceImpl;

    public JwtAuthorizationFilter(UserDetailsService userDetailsService) {
        this.shieldJdbcUserDetailsServiceImpl = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(SecurityConstants.TOKEN_KEY);
        if (!StringUtils.isEmpty(token)) {
            String username = JwtUtil.getUserNameFromToken(token);
            if (!StringUtils.isEmpty(username)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = shieldJdbcUserDetailsServiceImpl.loadUserByUsername(username);
                if (JwtUtil.checkToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
