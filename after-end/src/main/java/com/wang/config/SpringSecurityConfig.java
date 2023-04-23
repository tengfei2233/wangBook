package com.wang.config;

import com.wang.common.RoleEnum;
import com.wang.exception.AccessDeniedHandlerImpl;
import com.wang.exception.AuthenticationEntryPointImpl;
import com.wang.service.JwtAuthenticationTokenFilter;
import com.wang.service.LogoutService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
    @Resource
    private AccessDeniedHandlerImpl accessDeniedHandler;
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private LogoutService logoutService;

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     *
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 异常处理
                .exceptionHandling(handle -> handle
                        // 认证异常（没登陆）
                        .authenticationEntryPoint(authenticationEntryPoint)
                        // 访问异常（没权限）
                        .accessDeniedHandler(accessDeniedHandler)
                )
                // 基于token，不需要csrf
                .csrf().disable()
                // 基于token，不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 设置权限
                .authorizeRequests(auth -> auth
                        // 请求放开
                        // swagger
                        .antMatchers("/favicon.ico", "/doc.html", "/webjars/**", "/swagger-resources/**", "/v3/api-docs/**").permitAll()
                        // 退出接口
                        // .antMatchers("/user/logout", "/manage/logout").permitAll()
                        // 方形接口（具体匹配接口放上面，防止匹配顺序问题）
                        .antMatchers("/user/login/login", "/user/login/getCaptcha", "/manage/login/login").permitAll()
                        // 普通用户接口
                        .antMatchers("/user/**").hasRole(RoleEnum.USER.name())
                        // 管理员接口
                        .antMatchers("/manage/**").hasRole(RoleEnum.ADMIN.name())
                        // 其它全部需要验证
                        .anyRequest().authenticated()
                )
                .logout().logoutUrl("/manage/logout").logoutSuccessHandler(logoutService).and()
                .logout().logoutUrl("/user/logout").logoutSuccessHandler(logoutService).and()
                // 访问前jwt认证（在UsernamePasswordAuthenticationFilter前加入jwt过滤器）
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 身份认证
                .userDetailsService(userDetailsService)
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
