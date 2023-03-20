package com.wang.config;

import com.wang.exception.AuthenticationEntryPointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-20-15:14
 * @description SpringSecurity5.4以后的配置
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 异常处理
                .exceptionHandling(handle -> handle
                        .authenticationEntryPoint(authenticationEntryPoint)
                )
                // 基于token，不需要csrf
                .csrf().disable()
                // 基于token，不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // 设置权限
                .authorizeRequests(auth -> auth
                        // 请求放开
                        .antMatchers("/favicon.ico", "/doc.html", "/webjars/**", "/swagger-resources/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                // TODO 身份认证
//                .userDetailsService(null)
                .build();
    }

    /**
     * 密码明文加密方式配置
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 跨域支持
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 所有请求都支持跨域
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
