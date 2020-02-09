package com.heylhh.user.filter;

import com.heylhh.common.util.JwtUtil;
import com.heylhh.user.pojo.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;


    /**
     * 判断权限拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        //获取请求的地址ַ
        String url = request.getRequestURI();
        //  对特殊地址，直接放行
        if (url.indexOf("login") > 0 ||url.indexOf("uploadImg") > 0 ||url.indexOf("doc") > 0 || url.indexOf("register") > 0 || url.indexOf("upload") > 0) {
            return true;
        }
        final String authHeader = request.getHeader("Authorization");
        String requestURI = request.getServletPath();
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7);
            try{
                Claims claims = jwtUtil.parseJWT(token);
                if (claims != null) {
                    if ("admin".equals(claims.get("roles"))) {//如果是管理员
                        request.setAttribute("admin_claims", claims);
                    }
                    if ("teacher".equals(claims.get("roles"))) {
                        request.setAttribute("teacher_claims", claims);
                    }
                    if ("student".equals(claims.get("roles"))) {
                        request.setAttribute("student_claims", claims);
                    }
                    request.setAttribute("claims", claims.get("roles"));
                }
            }catch (Exception e){
                throw new RuntimeException("令牌不正确");
            }
            return true;
        }

        //判断session，session存在的话，登录后台.getSession();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");
        if (user != null) {
            return true;
        }
        //ִ 执行这里表示用户身份需要验证，跳转到登录界面
        request.getRequestDispatcher("/login").forward(request, response);
        return false;

    }
}
