package site.wetsion.security.shield.utils;

/**
 * 常量
 *
 * Created by wetsion on 2020/1/11.
 */
public class SecurityConstants {

    public final static String[] PERMIT_URL = {"/login", "/logout", "/403", "/404", "/api/**"};

    public final static String TOKEN_KEY = "Authorization";
}
