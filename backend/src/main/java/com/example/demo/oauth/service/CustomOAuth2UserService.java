package com.example.demo.oauth.service;

import com.example.demo.api.user.entity.MyUser;
import com.example.demo.api.user.repository.UserRepository;
import com.example.demo.oauth.entity.MyUserDetails;
import com.example.demo.oauth.entity.ProviderType;
import com.example.demo.oauth.entity.RoleType;
import com.example.demo.oauth.exception.OAuthProviderMismatchException;
import com.example.demo.oauth.userInfo.OAuth2UserInfo;
import com.example.demo.oauth.userInfo.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ae) {
            throw ae;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {

        ProviderType pType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(pType, user.getAttributes());
        MyUser savedUser = userRepo.findByEmail(userInfo.getEmail());

        if(savedUser != null) {
            if(pType != savedUser.getProviderType()) {
                throw new OAuthProviderMismatchException("이미 " + pType + "으로 가입하셨습니다. " + savedUser.getProviderType() + " 계정으로 로그인 해주세요!" );
            }
            updateUser(savedUser, userInfo);
        } else {
            savedUser = createUser(userInfo, pType);
        }

        return MyUserDetails.create(savedUser, user.getAttributes());

    }

    private MyUser updateUser(MyUser user, OAuth2UserInfo userInfo) {

        if(userInfo.getName() != null && !user.getUsername().equals(userInfo.getName())) {
            user.setUsername(userInfo.getName()); // dto - entity 매핑이 필요한가?
        }

        if(userInfo.getImageUrl() != null && !user.getProfileImgUrl().equals(userInfo.getImageUrl())) {
            user.setProfileImgUrl(userInfo.getImageUrl());
        }

        return user;
    }

    private MyUser createUser(OAuth2UserInfo userInfo, ProviderType pType) {

        LocalDateTime now = LocalDateTime.now();

        MyUser user = new MyUser(userInfo.getEmail(), userInfo.getName(), userInfo.getImageUrl(), pType, RoleType.USER, "Y", now, now);

        return userRepo.saveAndFlush(user);

    }

}
