package com.dragon.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author tdragon.
 * @Date 2021/1/12.
 * @Time 17:28
 * @Description:
 */

public class MyInter1 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        System.out.println("用户:" + session.getAttribute("user_session"));
        if (session.getAttribute("user_session") != null) {
            //放行
            System.out.println("用户检验通过，运行访问！");
            return true;
        }
        System.out.println("拦截访问成功！");
        response.sendRedirect("/Student/loginForm.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("post---handle后响应前");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after-- 视图渲染之后 可进行资源回收");
    }
}
