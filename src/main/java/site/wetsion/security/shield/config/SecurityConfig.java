package site.wetsion.security.shield.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * Created by wetsion on 2020/1/8.
 *
 * @author weixin
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/**").authenticated()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                    // 配置登录页地址，并且让所有用户可以访问
                    .loginPage("/login").permitAll()
//                    .loginProcessingUrl("/doLogin")
//                    .failureUrl("/login?error=true")
        ;
    }
}
