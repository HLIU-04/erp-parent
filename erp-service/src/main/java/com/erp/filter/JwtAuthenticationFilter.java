package com.erp.filter;

import com.erp.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. 从请求头中获取 token
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token)) {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            // 否则直接使用 token
        } else {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 2. 验证 token
            if (jwtUtil.validateToken(token)) {
                // 3. 从 token 中获取用户信息
                Integer userId = jwtUtil.getUserIdFromToken(token);
                String username = jwtUtil.getUsernameFromToken(token);
                String role = jwtUtil.getRoleFromToken(token);
                Integer deptId = jwtUtil.getDeptIdFromToken(token);

                // 4. 创建 UserDetails 对象（Spring Security 需要）
                // 这里简单构建，实际可以自定义 UserDetails 实现，包含角色等
                // 创建权限列表
                List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, "", authorities);
                log.info("用户 {} 的权限: {}", username, authorities);

                // 5. 创建认证令牌
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(deptId);

                // 6. 将认证信息存入 SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);

                log.info("已设置认证信息，用户：{}", username);

                log.debug("用户 {} 认证成功，角色：{}", username, role);
            } else {
                log.warn("无效的 token: {}", token);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"code\":401,\"msg\":\"无效的token\"}");
                return;  // 不再继续执行过滤器链
            }
        } catch (ExpiredJwtException e) {
            log.warn("token 已过期: {}", e.getMessage());
        } catch (SignatureException | MalformedJwtException e) {
            log.warn("token 签名无效: {}", e.getMessage());
        } catch (Exception e) {
            log.error("token 解析异常", e);
        }

        filterChain.doFilter(request, response);
    }
}
