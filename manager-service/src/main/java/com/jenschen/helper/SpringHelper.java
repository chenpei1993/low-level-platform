package com.jenschen.helper;

import com.jenschen.constant.CommonConstant;
import com.jenschen.exception.BizException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class SpringHelper {

    private SpringHelper() {

    }

    public static Integer getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new BizException("用户名为空");
        }
        Jwt jwt = (Jwt) auth.getPrincipal();
        return jwt.getClaim(CommonConstant.USER_ID);
    }

    public static String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new BizException("用户名为空");
        }
        Jwt jwt = (Jwt) auth.getPrincipal();
        return jwt.getClaim(CommonConstant.USERNAME);
    }

    public static void setNull() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
