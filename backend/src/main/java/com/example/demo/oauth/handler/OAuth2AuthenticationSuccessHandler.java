package com.example.demo.oauth.handler;

import com.example.demo.api.user.entity.UserRefreshToken;
import com.example.demo.api.user.repository.UserRefreshTokenRepository;
import com.example.demo.config.properties.AppProperties;
import com.example.demo.oauth.entity.ProviderType;
import com.example.demo.oauth.entity.RoleType;
import com.example.demo.oauth.repository.OAuth2AuthorizationRepository;
import com.example.demo.oauth.token.AuthToken;
import com.example.demo.oauth.token.AuthTokenProvider;
import com.example.demo.oauth.userInfo.OAuth2UserInfo;
import com.example.demo.oauth.userInfo.OAuth2UserInfoFactory;
import com.example.demo.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthTokenProvider tokenProvider;
    private final AppProperties appProps;
    private final UserRefreshTokenRepository userRefreshTokenRepo;
    private final OAuth2AuthorizationRepository authRepo;

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authRepo.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);

        return appProps.getOauth2().getAuthorizedRedirectUris().stream()
                .anyMatch(authorizedRedirectUri -> {
                    URI authorizedUri = URI.create(authorizedRedirectUri);
                    if(authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) && authorizedUri.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    }
                    return false;
                });
    }

    private boolean hasAuthority(Collection<? extends GrantedAuthority> authorities, String authority) {
        if(authorities == null) {
            return false;
        }

        for(GrantedAuthority grantedAuthority : authorities) {
            if(authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }

        return false;

    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String targetUrl = determineTargetUrl(request, response, authentication);

        if(response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }


    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtil.getCookie(request, OAuth2AuthorizationRepository.REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException("승인되지 않은 URI 입니다. 인증을 진행할 수 없습니다.");
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken)authentication;
        ProviderType pType = ProviderType.valueOf(authToken.getAuthorizedClientRegistrationId().toUpperCase());

        OidcUser user = (OidcUser)authentication.getPrincipal();
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(pType, user.getAttributes());
        Collection<? extends GrantedAuthority> authorities = ((OidcUser)authentication.getPrincipal()).getAuthorities();

        // ternary operator if-else
        RoleType roleType = hasAuthority(authorities, RoleType.ADMIN.getCode()) ? RoleType.ADMIN : RoleType.USER;

        Date now = new Date();
        AuthToken accessToken = tokenProvider.createAuthToken(userInfo.getEmail(), roleType.getCode(), new Date(now.getTime() + appProps.getAuth().getTokenExpiry()));

        long refreshTokenExpiry = appProps.getAuth().getRefreshTokenExpiry();

        AuthToken refreshToken = tokenProvider.createAuthToken(appProps.getAuth().getTokenSecret(), new Date(now.getTime() + refreshTokenExpiry));

        UserRefreshToken userRefreshToken = userRefreshTokenRepo.findByEmail(userInfo.getEmail());
        if(userRefreshToken != null) {
            userRefreshToken.setRefreshToken(refreshToken.getToken());
        } else{
            userRefreshToken = new UserRefreshToken(userInfo.getEmail(), refreshToken.getToken());
            userRefreshTokenRepo.saveAndFlush(userRefreshToken);
        }

        int cookieMaxAge = (int)refreshTokenExpiry / 60;

        CookieUtil.deleteCookie(request, response, OAuth2AuthorizationRepository.REFRESH_TOKEN);
        CookieUtil.addCookie(response, OAuth2AuthorizationRepository.REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);

        return UriComponentsBuilder.fromUriString(targetUrl).queryParam("token", accessToken.getToken()).build().toUriString();

    }



}
