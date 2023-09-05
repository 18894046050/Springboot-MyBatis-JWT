package com.cbhx.config;


import com.cbhx.entity.AuthToken;
import com.cbhx.mapper.AuthTokenMapper;
import com.cbhx.utils.JsonUtil;
import com.cbhx.utils.JwtTokenUtil;
import com.cbhx.utils.ResponseUtil;
import com.cbhx.wrapper.ApiResponse;
import com.cbhx.wrapper.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * jwt认证过滤器
 *
 * @author WangJiangQi
 * @since 2023-02-06
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/**/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // other public endpoints of your API may be appended to this array
            "/api/v1/user/login",
            "/v2/api-docs",
            "js",
            "css",
            "/actuator/prometheus",
            "/api/v1/rule/category-tree",
            "/api/v1/rule/list",
            "/api/v1/data_collection/*",
            "/api/v1/rule/list/*",
            "/api/v1/user/IDP/login"
};

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private AuthTokenMapper authTokenMapper;

    private static void printJson(HttpServletResponse response, String errMessage) {
        if (errMessage.equals(Status.TOKEN_EXPIRED.getMessage())) {
            ApiResponse apiResponse = ApiResponse.ofStatus(Status.TOKEN_EXPIRED);
            String content = JsonUtil.toJsonString(apiResponse);
            printContent(response, content);
        } else if (errMessage.equals(Status.TOKEN_LOGIN.getMessage())) {
            ApiResponse apiResponse = ApiResponse.ofStatus(Status.TOKEN_LOGIN);
            String content = JsonUtil.toJsonString(apiResponse);
            printContent(response, content);
        } else {
            ApiResponse apiResponse = ApiResponse.ofStatus(Status.TOKEN_PARSE_ERROR);
            String content = JsonUtil.toJsonString(apiResponse);
            printContent(response, content);
        }
    }

    private static void printContent(HttpServletResponse response, String content) {
        try {
            response.reset();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (permitUrl(request, response, filterChain)) {
            return;
        }

        String jwt = JwtTokenUtil.getJwtFromRequest(request);

        if (StringUtils.isBlank(jwt)) {
            ResponseUtil.renderJson(response, Status.UNAUTHORIZED, null);
        } else {
            try {
                String username = JwtTokenUtil.getIssuer(jwt);

                AuthToken authToken = authTokenMapper.selectAuthTokenByUsername(username);
                if (null == authToken) {
                    printJson(response, Status.TOKEN_EXPIRED.getMessage());
                    return;
                }

                filterChain.doFilter(request, response);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                printJson(response, e.getMessage());
            }
        }

    }

    private boolean permitUrl(HttpServletRequest request, HttpServletResponse response,
                              FilterChain filterChain) throws IOException, ServletException {

        boolean flag = false;
        for (String authPath : AUTH_WHITELIST) {
            flag = antPathMatcher.match(authPath, request.getRequestURI()) || flag;
        }

        if (flag) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }

}
