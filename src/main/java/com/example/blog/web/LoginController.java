package com.example.blog.web;

import com.example.blog.core.ServerResponse;
import com.example.blog.model.Roles;
import com.example.blog.model.TUser;
import com.example.blog.service.RolesService;
import com.example.blog.service.TUserService;
import com.example.blog.util.StringTools;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @ClassName LoginController
 * @Author chenxue
 * @Description 登录controller
 * @Date 2019/6/24 18:13
 **/
@RestController
@Slf4j
public class LoginController {
    @Resource
    private TUserService tUserService;
    @Resource
    private RolesService rolesService;

    @ApiOperation(value = "登录",notes = "登录")
    @PostMapping("/login")
    public ServerResponse login(@RequestParam String username, @RequestParam String password,HttpServletRequest request) {
        if(StringTools.isEmpty(username)){
            return ServerResponse.createByErrorCodeMessage(1,"用户名为空");
        }
        if(StringTools.isEmpty(password)){
            return ServerResponse.createByErrorCodeMessage(1,"密码为空");
        }
        //启用shiro 登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            if(request.getAttribute("rememberMe") != null){
                token.setRememberMe(true);
            }
            TUser tUser = tUserService.getUserByUsername(username);
            if(tUser == null){
                return ServerResponse.createByErrorCodeMessage(1,"获取用户信息失败!");
            }
            List<Roles> rolesList = rolesService.getRoleByUserId(tUser.getId());
            List<String> roleList = new ArrayList<>();
            for (Roles roles : rolesList){
                roleList.add(roles.getRoleKey());
            }
            subject.login(token);
            subject.hasRoles(roleList);
            return ServerResponse.createBySuccess("登录成功!");
        }catch (UnknownAccountException e){
            log.info("用户名或密码错误");
            return ServerResponse.createByErrorCodeMessage(1,"登录失败：密码错误!");
        }catch (IncorrectCredentialsException e){
            log.info("用户名或密码错误");
            return ServerResponse.createByErrorCodeMessage(1,"登录失败：密码错误!");
        }catch (LockedAccountException e){
            log.info("用户名或密码错误");
            return ServerResponse.createByErrorCodeMessage(1,"登录失败：该用户已被冻结!");
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByErrorCodeMessage(1,"登录异常：" + e.getMessage());
        }
    }

    @ApiOperation(value = "退出",notes = "退出")
    @PostMapping("/logout")
    public ServerResponse logout(){
       Subject subject = SecurityUtils.getSubject();
       //清除 session
       subject.logout();
        return ServerResponse.createBySuccess();
    }

    @GetMapping("/unauthorized")
    @ApiOperation(value = "未授权，访问地址",notes = "未授权，访问地址")
    public ServerResponse unauthorized(){
        return ServerResponse.createByErrorCodeMessage(4,"没有权限!");
    }
}
