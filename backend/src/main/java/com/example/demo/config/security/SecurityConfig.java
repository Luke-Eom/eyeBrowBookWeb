package com.example.demo.config.security;

import com.example.demo.api.user.entity.UserRefreshToken;
import com.example.demo.api.user.repository.UserRefreshTokenRepository;
import com.example.demo.config.WebSecurityConfig;
import com.example.demo.config.properties.AppProperties;
import com.example.demo.config.properties.CorsProperties;
import com.example.demo.oauth.handler.TokenAccessDeniedHandler;
import com.example.demo.oauth.service.CustomOAuth2UserService;
import com.example.demo.oauth.service.CustomUserDetailsService;
import com.example.demo.oauth.token.AuthToken;
import com.example.demo.oauth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfig {

    private final CorsProperties corsProps;
    private final AppProperties AppProps;
    private final AuthTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final CustomOAuth2UserService oAuth2UserService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final UserRefreshTokenRepository userRefreshTokenRepo;




}
