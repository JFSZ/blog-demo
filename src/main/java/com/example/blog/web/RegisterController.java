package com.example.blog.web;

import com.example.blog.core.ServerResponse;
import com.example.blog.model.TUser;
import com.example.blog.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName RegistController
 * @Author chenxue
 * @Description
 * @Date 2019/6/24 18:13
 **/
@RestController
public class RegisterController {

    @Autowired
    TUserService tUserService;
    @PostMapping
    @RequestMapping("/register")
    public ServerResponse register(TUser user){
        int result = tUserService.register(user);
        if(-1 == result){
            return ServerResponse.createByErrorCodeMessage(1,"用户已存在!");
        }else if(result >= 1){
            return ServerResponse.createBySuccess("注册成功");
        }else {
            return ServerResponse.createByErrorCodeMessage(1,"注册失败");
        }
    }
}
