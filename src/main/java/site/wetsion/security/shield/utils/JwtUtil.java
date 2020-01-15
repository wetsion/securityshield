package site.wetsion.security.shield.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 *
 * @author weixin
 * @version 1.0
 * @date 2020/1/13 6:35 PM
 */
public class JwtUtil {

    private final static Long EXPIRE_TIME = 432000000L;

    private final static String SALT = "shield";

    private final static String TOKEN_KEY = "name";

    /**
     * 生成jwt
     *
     * @param userDetails
     * @author weixin
     * @date 6:41 PM 2020/1/13
     * @return java.lang.String
     **/
    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(TOKEN_KEY, userDetails.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS512, SALT)
                .compact();
    }

    /**
     * 校验 jwt
     *
     * @param token
     * @param userDetails
     * @author weixin
     * @date 6:52 PM 2020/1/13
     * @return boolean
     **/
    public static boolean checkToken(String token, UserDetails userDetails) {
        return getUserNameFromToken(token).equals(userDetails.getUsername())
                && !isExpired(token);
    }

    public static String getUserNameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public static boolean isExpired(String token) {
        return getExpireFromToken(token).before(new Date());
    }

    public static Date getExpireFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SALT)
                .parseClaimsJws(token)
                .getBody();
    }
}
