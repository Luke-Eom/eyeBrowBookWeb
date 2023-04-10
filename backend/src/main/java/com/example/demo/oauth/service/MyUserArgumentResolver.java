package com.example.demo.oauth.service;

import com.example.demo.api.user.entity.MyUser;
import com.example.demo.oauth.entity.MyUserDetails;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MyUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return MyUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // instanceof 를 통해 authentication이 AnonymousAuthenticationToken 또는 하위 클래스에 속하는 인스턴스인지 확인
        // 아닐 경우 getMyUser()로 맞춰서 반환
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            return ((MyUserDetails)authentication.getPrincipal()).getMyUser();
        }

        return null;
    }

}
