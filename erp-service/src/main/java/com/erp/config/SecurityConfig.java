package com.erp.config;

import com.erp.filter.JwtAuthenticationFilter;
import com.erp.result.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity//启用Spring Security的Web安全功能，让自定义的安全配置生效
@Configuration//spring配置类，容器启动时加载
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 密码加密器
    }

    // 暂时提供一个空的 UserDetailsService，避免警告（后续会被 JWT 过滤器替代）
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(); // 空实现
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/dept.html",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/favicon.ico"
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .authorizeHttpRequests(auth -> auth
                        // 1. 公开资源
                        .requestMatchers("/login", "/login.html", "/index.html", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg").permitAll()
//
//                        // 2. 员工和管理员都可访问的 GET 接口（只读数据）
//                        .requestMatchers(HttpMethod.GET, "/admin/depts/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
//                        .requestMatchers(HttpMethod.GET, "/admin/customers/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
//                        .requestMatchers(HttpMethod.GET, "/admin/products/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
//                        .requestMatchers(HttpMethod.GET, "/admin/daily-report/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
//
//                        // 3. 管理员专用 PUT 接口（修改日报）
//                        .requestMatchers(HttpMethod.PUT, "/admin/daily-report/**").hasAuthority("ADMIN")
//
//                        // 4. 管理员所有其他接口
//                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
//
//                        // 5. 员工所有接口（包括日报发货 /delivery 和结存 /remaining）
//                        .requestMatchers("/employee/**").hasAuthority("EMPLOYEE")

                        // 6. 其他请求需要认证
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
                .exceptionHandling(handling -> handling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType("application/json;charset=UTF-8");
                            String json = new ObjectMapper().writeValueAsString(Result.error("权限不足，无法访问"));
                            response.getWriter().write(json);
                        })
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
