package com.example.blog.config.shiro;


import com.example.blog.util.CookieUtil;
import com.example.blog.util.RedisUtil;
import com.example.blog.util.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @ClassName MySessionManager
 * @Author chenxue
 * @Description
 * @Date 2019/7/10 10:56
 **/
@Slf4j
public class MySessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
    @Autowired
    private CookieUtil cookieUtil;
    @Autowired
    private RedisUtil redisUtil;

    public MySessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        // 如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(id)) {
            log.info("请求头中获取");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            String sessionId = StringTools.stringof(redisUtil.get(id));
            return sessionId;
        } else {
            String cookieToken = cookieUtil.getCookie((HttpServletRequest) request,"online-login");
            if(!StringTools.isEmpty(cookieToken)){
                String sessionId = StringTools.stringof(redisUtil.get(cookieToken));
                return sessionId;
            }else {
                log.info("默认方式获取sessionId");
                // 否则按默认规则从cookie取sessionId
                return super.getSessionId(request, response);
            }
        }
    }

}
