package com.example.blog.web.admin;

import com.example.blog.core.ServerResponse;
import com.example.blog.model.Category;
import com.example.blog.model.Roles;
import com.example.blog.model.TUser;
import com.example.blog.service.CategoryService;
import com.example.blog.service.RolesService;
import com.example.blog.service.TUserService;
import com.example.blog.util.StringTools;
import com.example.blog.vo.TUserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AdminController
 * @Author chenxue
 * @Description 超管controller
 * @Date 2019/6/28 9:33
 **/
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private TUserService tUserService;
    @Resource
    private RolesService rolesService;

    @ApiOperation(value = "根据关键字,获取所有用户信息",notes = "根据关键字,获取所有用户信息")
    @PostMapping("/getAllUserInfo")
    public ServerResponse getAllUserInfo(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer size,String keywords){
        PageHelper.startPage(page,size);
        List<TUserVo> voList = tUserService.getAllUserInfo(keywords);
        PageInfo pageInfo = new PageInfo(voList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @ApiOperation(value = "根据id，获取用户信息",notes = "根据id，获取用户信息")
    @PostMapping("/getUserById")
    public ServerResponse getUserById(Integer id){
        if(id == null){
            return ServerResponse.createByErrorCodeMessage(1,"参数为空");
        }
        return tUserService.getUserById(id);
    }

    @ApiOperation(value = "获取所有角色",notes = "获取所有角色")
    @PostMapping("/getAllRoles")
    public ServerResponse getAllRoles(){
        List<Roles> rolesList = rolesService.findAll();
        return ServerResponse.createBySuccess(rolesList);
    }

    /*
    * @Author chenxue
     * @Description 更新用户角色
     * @Param rids角色id 集合
     *@Param id 用户id
     * @Date 2019/7/2 13:43
     **/

    @ApiOperation(value = "更新用户角色",notes = "更新用户角色")
    @PostMapping("/updateUserRoles")
    public ServerResponse updateUserRoles(String rids,Integer id){
        if(StringTools.isEmpty(rids)){
            return ServerResponse.createByErrorCodeMessage(1,"参数为空");
        }
        if(id == null){
            return ServerResponse.createByErrorCodeMessage(1,"参数为空");
        }
        return tUserService.updateUserRoles(rids,id);
    }

    /*
    * @Author chenxue
     * @Description 配置用户是否可见
     * @Date 2019/7/3 10:43
     * @Param []
     **/

    @ApiOperation(value = "配置用户是否可见",notes = "配置用户是否可见")
    @PostMapping("/enabled")
    public ServerResponse enabled(Boolean enabled,Integer id){
        if(enabled == null){
            return ServerResponse.createByErrorCodeMessage(1,"参数为空");
        }
        if(id == null){
            return ServerResponse.createByErrorCodeMessage(1,"参数为空");
        }
        return tUserService.enabled(enabled,id);
    }

    /*
    * @Author chenxue
     * @Description 删除用户
     * @Date 2019/7/3 15:44
     * @Param [id]
     **/

    @ApiOperation(value = "删除用户",notes = "删除用户")
    @PostMapping("/delete")
    public ServerResponse delete(Integer id){
        return tUserService.deleteByUserId(id);
    }


    /*
    * @Author chenxue
     * @Description 新增文章分类
     * @Date 2019/7/3 15:46
     * @Param cateName 文章分类名称
     **/

    @ApiOperation(value = "新增文章分类",notes = "新增文章分类")
    @PostMapping("/addCategory")
    public ServerResponse addCategory(Category category){
        if(category == null || category.getCatename() == null){
            return ServerResponse.createByErrorCodeMessage(1,"参数为空");
        }
        category.setState(0);
        category.setDate(new Date());
        int result = categoryService.save(category);
        if(result == 1){
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorCodeMessage(1,"新增分类失败");
        }
    }

    @ApiOperation(value = "删除文章分类",notes = "删除文章分类")
    @PostMapping("/deleteCategory")
    public ServerResponse deleteCategory(String ids){
        if(StringTools.isEmpty(ids)){
            return ServerResponse.createByErrorCodeMessage(1,"参数为空");
        }
        return categoryService.deleteCategory(ids);
    }

    @ApiOperation(value = "修改文章分类",notes = "修改文章分类")
    @PostMapping("/editCategory")
    public ServerResponse editCategory(Category category){
        return categoryService.editCategory(category);
    }


}
