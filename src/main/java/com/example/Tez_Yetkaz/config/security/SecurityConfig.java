package com.example.Tez_Yetkaz.config.security;

import com.example.Tez_Yetkaz.security.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.Tez_Yetkaz.enums.RoleType.ADMIN;
import static com.example.Tez_Yetkaz.enums.RoleType.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final AuthenticationProvider authenticationProvider;
    private final JWTFilter jwtFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomBearerTokenAccessDeniedHandler customBearerTokenAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "api/v1/auth/**",
                                "api/v1/category/get-all",
                                "api/v1/restaurant/get-all",
                                "api/v1/restaurant/get-all-by-category/",
                                "api/v1/food/get-all",
                                "api/v1/food/get-all-by-category/",
                                "api/v1/food/get-all-by-restaurant/",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html")
                        .permitAll()
                        .requestMatchers("api/v1/admin/**").hasAnyRole(ADMIN.name())
//                        .requestMatchers("api/v1/category/**").hasAnyRole(USER.name(), ADMIN.name())
//                        .requestMatchers("api/v1/finance/**").hasAnyRole(USER.name())
                        .requestMatchers("api/v1/user/**").hasAnyRole(USER.name())
                        .anyRequest()
                        .authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(httpSecurityExceptionHandling -> httpSecurityExceptionHandling
                        .authenticationEntryPoint(this.customAuthenticationEntryPoint)
                        .accessDeniedHandler(this.customBearerTokenAccessDeniedHandler));
        return httpSecurity.build();
    }
}
