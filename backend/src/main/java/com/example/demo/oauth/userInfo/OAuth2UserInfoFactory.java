package com.example.demo.oauth.userInfo;

import com.example.demo.oauth.entity.ProviderType;
import com.example.demo.oauth.userInfo.impl.GoogleOAuth2UserInfo;
import com.example.demo.oauth.userInfo.impl.KakaoOAuth2UserInfo;
import com.example.demo.oauth.userInfo.impl.NaverOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType pType, Map<String, Object> attributes){
        switch (pType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("유효하지 않은 매체입니다");
        }
    }
}
