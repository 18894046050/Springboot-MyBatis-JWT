package com.cbhx.utils;

import com.cbhx.wrapper.Status;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import com.cbhx.exception.SecurityException;

/**
 * @author WangJiangQi
 * @since 2023-02-06
 */
@Slf4j
public class JwtTokenUtil {

    /**
     * token名字
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * 前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 用户自定义密钥
     */
    public static final String SECRET = "jwt_secret_user_defined";
    public static final String ISS = "user_defined";

    /**
     * 过期时间 毫秒
     */
    public static final long EXPIRATION = 12L * 60L * 60L * 1000L;

    public static String createToken(String issuer, String subject) {
        return createToken(issuer, subject, EXPIRATION, null);
    }

    public static String createToken(String issuer, String subject, long expiration) {
        return createToken(issuer, subject, expiration, null);
    }

    /**
     * 创建 token
     *
     * @param issuer     签发人
     * @param subject    主体,即用户信息的JSON
     * @param expiration 有效时间(秒)
     * @param claims     自定义参数
     */
    public static String createToken(String issuer, String subject, long expiration,
                                     Claims claims) {
        return TOKEN_PREFIX + Jwts.builder()
                // JWT_ID：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
//                .setId(id)
                // 签名算法以及密匙
                .signWith(SignatureAlgorithm.HS512, SECRET)
                // 自定义属性
                .setClaims(null)
                // 主题：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userId，roleId之类的，作为用户的唯一标志。
                .setSubject(subject)
                // 受众
//                .setAudience(loginName)
                // 签发人
                .setIssuer(Optional.ofNullable(issuer).orElse(ISS))
                // 签发时间
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(new Date(
                        System.currentTimeMillis() + (expiration > 0 ? expiration : EXPIRATION)))
                .compact();
    }

    /**
     * 从 token 中获取主题信息
     */
    public static String getSubject(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 从 token 中获取主题信息
     */
    public static String getIssuer(String token) {
        return getTokenBody(token).getIssuer();
    }

    /**
     * 校验是否过期
     */
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 获得 token 的 body
     */
    private static Claims getTokenBody(String token) {

        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            log.error("Token 已过期", e);
            throw new SecurityException(Status.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error("不支持的 Token", e);
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        } catch (MalformedJwtException e) {
            log.error("Token 无效", e);
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        } catch (SignatureException e) {
            log.error("无效的 Token 签名", e);
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("Token 参数不存在", e);
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        }
    }

    /**
     * 从 request 的 header 中获取 JWT
     *
     * @param request 请求
     * @return JWT
     */
    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
