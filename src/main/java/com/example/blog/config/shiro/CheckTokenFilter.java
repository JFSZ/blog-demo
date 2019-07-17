package com.example.blog.config.shiro;

import com.example.blog.util.CookieUtil;
import com.example.blog.util.RedisUtil;
import com.example.blog.util.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CheckTokenFilter
 * @Author chenxue
 * @Description 拦截请求,验证token是否有效
 * AccessControlFilter 用于控制对资源的访问的任何过滤器的超类，如果未经过身份验证，则可以将用户重定向到登录页面。
 * @Date 2019/7/11 16:29
 **/
@Slf4j
public class CheckTokenFilter extends AccessControlFilter {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CookieUtil cookieUtil;
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //如果有不需要登录的url，可在这里放行
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        /*Subject subject = getSubject(request,response);
        if(!subject.isAuthenticated() || !subject.isRemembered()){
            //如果没有登录，直接进行之后的流程
            return true;
        }*/
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String onlineCookie = cookieUtil.getCookie(httpServletRequest,"online-login");
        if(!StringTools.isEmpty(onlineCookie)){
            return true;
        }else {
            httpServletResponse.setHeader("content-type", "application/x-www-form-urlencoded;charset=UTF-8");
            httpServletResponse.getWriter().write("{\"code\":2,\"message\":\"登录失效\",\"data\":\"\",\"success\":false}");
            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();
            return false;
        }
    }
}
