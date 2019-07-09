package com.example.blog.web;
import com.example.blog.core.ServerResponse;
import com.example.blog.model.Roles;
import com.example.blog.model.TUser;
import com.example.blog.service.TUserService;
import com.example.blog.util.StringTools;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/*
* @Author  陈学
 * @Description
 * @Date 2019-06-24 18:08:13
 **/

@RestController
@RequestMapping("/user")
@Slf4j
public class TUserController {
    @Resource
    private TUserService tUserService;

    @ApiOperation(value = "新增",notes = "新增")
    @PostMapping("/add")
    public ServerResponse add(TUser tUser) {
        tUserService.save(tUser);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "删除",notes = "删除")
    @PostMapping("/delete")
    public ServerResponse delete(@RequestParam Integer id) {
        tUserService.deleteById(id);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "更新",notes = "更新")
    @PostMapping("/update")
    public ServerResponse update(TUser tUser) {
        tUserService.update(tUser);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "查询详情",notes = "查询详情")
    @PostMapping("/detail")
    public ServerResponse detail(@RequestParam Integer id) {
        TUser tUser = tUserService.findById(id);
        return ServerResponse.createBySuccess(tUser);
    }

    @ApiOperation(value = "查询列表",notes = "查询列表")
    @PostMapping("/list")
    public ServerResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TUser> list = tUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @ApiOperation(value = "查询列表",notes = "查询列表")
    @PostMapping("/getUserByUsername")
    public ServerResponse getUserByUsername(@RequestParam String username){
        if(StringTools.isEmpty(username)){
            return ServerResponse.createByErrorCodeMessage(3,"用户名为空!");
        }
        TUser tUser = tUserService.getUserByUsername(username);
        if(tUser != null ){
            return ServerResponse.createBySuccess(tUser);
        }else {
            return ServerResponse.createByErrorCodeMessage(1,"未查询到相关用户!");
        }
    }

    @RequestMapping("/currentUserName")
    public ServerResponse currentUserName(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if( StringTools.isEmpty(username)){
            return ServerResponse.createByErrorCodeMessage(2,"获取登录人信息失败,请重新登录!");
        }
        TUser tUser = tUserService.getUserByUsername(username);
        return ServerResponse.createBySuccess(tUser);

    }
    @PostMapping("/isAdmin")
    public ServerResponse isAdmin(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if( StringTools.isEmpty(username)){
            return ServerResponse.createByErrorCodeMessage(2,"获取登录人信息失败,请重新登录!");
        }
        //判断是否是超管
        List<Roles> rolesList = tUserService.isAdmin(username);
        for (Roles roles : rolesList){
            if(1 == roles.getId()){
                return ServerResponse.createBySuccess();
            }
        }
        return ServerResponse.createByErrorCodeMessage(1,"当前登录人不是超管!");
    }


    /*
    * @Author chenxue
     * @Description 更新用户邮箱
     * @Date 2019/6/28 14:16
     * @Param []
     * @return com.example.blog.core.ServerResponse
     **/

    @PostMapping("/updateUserEmail")
    @ApiOperation(value = "更新用户邮箱",notes = "更新用户邮箱")
    public ServerResponse updateUserEmail(@RequestParam String email){
        if(StringTools.isEmpty(email)){
            return ServerResponse.createByErrorCodeMessage(1,"参数为空");
        }
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if( StringTools.isEmpty(username)){
            return ServerResponse.createByErrorCodeMessage(2,"获取登录人信息失败,请重新登录!");
        }
        TUser tUser = tUserService.getUserByUsername(username);
        if(tUser != null){
            tUser.setEmail(email);
            tUserService.update(tUser);
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorCodeMessage(1,"更新用户信息失败");
        }
    }
}
