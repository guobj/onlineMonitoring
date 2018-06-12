package com.nz.onlineMonitoring.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class SessionInterceptor extends HandlerInterceptorAdapter {
    
    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response , Object handler )
            throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write("<script>"
                    + "alert('请登录');"
                    + "window.top.location.href='"+request.getContextPath()+"/login';"
                    + "</script>");
            out.flush();
            out.close();
            //response.sendRedirect("/error");
//            request.getRequestDispatcher("/error").forward(request,response);
            System.out.println("BKJkjzxnckjndcsdksns"+request.getContextPath());
            return false;
        }
        return true;
    }

//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        super.afterCompletion(request, response, handler, ex);
//    }
}
