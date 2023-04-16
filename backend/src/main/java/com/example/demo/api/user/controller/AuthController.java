package com.example.demo.api.user.controller;

import com.example.demo.api.user.dto.JoinDto;
import com.example.demo.api.user.dto.LoginDto;
import com.example.demo.api.user.entity.UserRefreshToken;
import com.example.demo.api.user.repository.UserRefreshTokenRepository;
import com.example.demo.api.user.service.UserService;
import com.example.demo.common.ApiResponse;
import com.example.demo.config.properties.AppProperties;
import com.example.demo.oauth.entity.MyUserDetails;
import com.example.demo.oauth.token.AuthToken;
import com.example.demo.oauth.token.AuthTokenProvider;
import com.example.demo.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final AuthenticationManager authManager;
    private final UserRefreshTokenRepository userRefreshTokenRepo;
    private final UserService userService;
    private final static long THREE_DAYS_MSEC = 259200000;
    private final static String REFRESH_TOKEN = "refresh_token";

    @RequestMapping(value ="/join", method = RequestMethod.POST, consumes = {"multipart/form-data" })
    public ApiResponse join(@ModelAttribute JoinDto joinDto) {

        userService.joinUser(joinDto);

        return ApiResponse.success("msg", "회원가입이 완료되었습니다");
    }

    @PostMapping("/login")
    public ApiResponse login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDto authReqModel) {

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authReqModel.getEmail(), authReqModel.getPassword()));
        String userEmail = authReqModel.getEmail();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Date now = new Date();
        AuthToken accessToken = tokenProvider.createAuthToken(
                userEmail,
                ((MyUserDetails)authentication.getPrincipal()).getMyUser().getRoleType().getCode(),
                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry()));

        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();
        AuthToken refreshToken = tokenProvider.createAuthToken(
                appProperties.getAuth().getTokenSecret(),
                new Date(now.getTime() + refreshTokenExpiry)
        );

        UserRefreshToken userRefreshToken = userRefreshTokenRepo.findByEmail(userEmail);
        if(userRefreshToken == null) {
            userRefreshToken = new UserRefreshToken(userEmail, refreshToken.getToken());
            userRefreshTokenRepo.saveAndFlush(userRefreshToken);
        } else {
            userRefreshToken.setRefreshToken(refreshToken.getToken());
        }

        int cookieMaxAge = (int)refreshTokenExpiry / 60;
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtil.addCookie(response, REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);

        return ApiResponse.success("token", accessToken.getToken());

    }




}
