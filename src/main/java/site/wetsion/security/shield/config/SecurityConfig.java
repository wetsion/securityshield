package site.wetsion.security.shield.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import site.wetsion.security.shield.filter.JwtAuthenticationFilter;
import site.wetsion.security.shield.filter.JwtAuthorizationFilter;
import site.wetsion.security.shield.handler.SecurityAuthenticationFailHandler;
import site.wetsion.security.shield.handler.SecurityAuthenticationSuccessHandler;
import site.wetsion.security.shield.utils.SecurityConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Created by wetsion on 2020/1/8.
 *
 * @author weixin
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService shieldJdbcUserDetailsServiceImpl;

    @Autowired
    private AccessDeniedHandler urlAccessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private FilterInvocationSecurityMetadataSource urlPermissionFilterInvocationSecurityMetadataSource;

    @Autowired
    private AccessDecisionManager urlAccessDecisionManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(shieldJdbcUserDetailsServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                        @Override
                        public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                            // 配置 SecurityMetadataSource
                            object.setSecurityMetadataSource(urlPermissionFilterInvocationSecurityMetadataSource);
                            return object;
                        }
                    })
                    // 配置 accessDecisionManager
                    .accessDecisionManager(urlAccessDecisionManager)
                    .antMatchers(SecurityConstants.PERMIT_URL).permitAll()
                    .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    // 配置登录页地址，并且让所有用户可以访问
                    .loginPage("/login").permitAll()
                    .loginProcessingUrl("/doLogin")
                    .successHandler(new InnerSuccessHandler())
                    .failureHandler(new InnerFailureHandler())
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                .and()
                .csrf()
                    .disable()
                // 异常 错误页处理
                .exceptionHandling()
                    // 匿名用户访问异常处理
                    .authenticationEntryPoint(authenticationEntryPoint)
                    // 已认证的用户访问异常处理
                    .accessDeniedHandler(urlAccessDeniedHandler)
        ;
        http
                .addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtAuthorizationFilter(shieldJdbcUserDetailsServiceImpl),
//                        UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new SecurityAuthenticationSuccessHandler());
        jwtAuthenticationFilter.setAuthenticationFailureHandler(new SecurityAuthenticationFailHandler());
        jwtAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return jwtAuthenticationFilter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(UserDetailsService shieldJdbcUserDetailsServiceImpl) {
        return new JwtAuthorizationFilter(shieldJdbcUserDetailsServiceImpl);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Slf4j
    static class InnerSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            log.info("登录成功");
            super.setDefaultTargetUrl("/");
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    @Slf4j
    static class InnerFailureHandler extends SimpleUrlAuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            log.info("登录失败");
            super.setDefaultFailureUrl("/login?error=true");
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
