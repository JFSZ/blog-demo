package com.example.blog.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CookieUtil
 * @Author chenxue
 * @Description cookie操作类
 * @Date 2019/7/11 15:40
 **/
@Component
@Slf4j
public class CookieUtil {
    //设置 domain 域名 解决应用 cookie 跨域问题
    public static final String COOKIE_DOMAIN = "";
    // cookie 默认名称
    public static final String COOKIE_NAME = "";

    /*
    *  @Author chenxue
     * @Description 写入cookie
     * @Date 2019/7/11 15:49
     * @param response
     * @param token
     * @return boolean
     **/
    public static CookieUtil getInstance(){
        return new CookieUtil();
    }

    public boolean setCookie(HttpServletResponse response,String token){
        log.info("设置cookie");
        if(StringTools.isEmpty(token)){
            log.info("CookieUtil -- setCookie token为空");
            return false;
        }

        Cookie cookie = new Cookie(COOKIE_NAME,token);
        // 设置在cookie访问权限 表示一个tomcat下的应用可以访问
        cookie.setPath("/");
        //防止脚本攻击泄露信息的风险
        cookie.setHttpOnly(true);
        //设置cookie 过期时间 30分钟
        cookie.setMaxAge(30*60);
        response.addCookie(cookie);
        return true;
    }
    /*
    *  @Author chenxue
     * @Description 获取cookie
     * @Date 2019/7/11 15:56
     * @param request
     * @return java.lang.String
     **/

    public String getCookie(HttpServletRequest request){
        log.info("getCookie");
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if(COOKIE_NAME.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /*
    *  @Author chenxue
     * @Description 删除cookie
     * @Date 2019/7/11 16:02
     * @param request
     * @param response
     * @return boolean
     **/

    public boolean deleteCookie(HttpServletRequest request,HttpServletResponse response){
        log.info("deleteCookie");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (COOKIE_NAME.equals(cookie.getName())){
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                //表示cookie过期
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return true;
            }
        }
        return false;
    }
    /*
     *  @Author chenxue
     * @Description 写入cookie
     * @Date 2019/7/11 15:49
     * @param response
     * @param token
     * @return boolean
     **/

    public boolean setCookie(HttpServletResponse response,String token,String cookieName){
        log.info("设置cookie");
        if(StringTools.isEmpty(token)){
            log.info("CookieUtil -- setCookie token为空");
            return false;
        }
        if(StringTools.isEmpty(cookieName)){
            return false;
        }
        Cookie cookie = new Cookie(cookieName,token);

        // 设置在cookie访问权限 表示一个tomcat下的应用可以访问
        cookie.setPath("/");
        //防止脚本攻击泄露信息的风险
        cookie.setHttpOnly(true);
        //设置cookie 过期时间 30分钟
        cookie.setMaxAge(30*60);
        response.addCookie(cookie);
        return true;
    }
    /*
     *  @Author chenxue
     * @Description 获取cookie
     * @Date 2019/7/11 15:56
     * @param request
     * @return java.lang.String
     **/

    public String getCookie(HttpServletRequest request,String cookieName){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if(cookieName.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    /*
     *  @Author chenxue
     * @Description 删除cookie
     * @Date 2019/7/11 16:02
     * @param request
     * @param response
     * @return boolean
     **/

    public boolean deleteCookie(HttpServletRequest request,HttpServletResponse response,String cookieName){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookieName.equals(cookie.getName())){
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                //表示cookie过期
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return true;
            }
        }
        return false;
    }
}
