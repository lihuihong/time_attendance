package com.heylhh.user.util;

import com.heylhh.user.pojo.User;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

public class UserInfoUtil {

    public static User getUserInfo(HttpServletRequest request){
        User user = new User();
        String roles = (String) request.getAttribute("claims");
        if("student".equals(roles)){
            Claims claims = (Claims) request.getAttribute("student_claims");
            user.setUserName(claims.getSubject());
            user.setUserType("0");
        }
        if("teacher".equals(roles)){
            Claims claims = (Claims) request.getAttribute("teacher_claims");
            user.setUserName(claims.getSubject());
            user.setUserType("1");
        }
        if("admin".equals(roles)){
            Claims claims = (Claims) request.getAttribute("admin_claims");
            user.setUserName(claims.getSubject());
            user.setUserType("2");
        }
        return user;
    }
}
