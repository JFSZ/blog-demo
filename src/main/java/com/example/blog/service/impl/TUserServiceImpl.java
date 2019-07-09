package com.example.blog.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.blog.core.ServerResponse;
import com.example.blog.dao.RolesMapper;
import com.example.blog.dao.RolesUserMapper;
import com.example.blog.dao.TUserMapper;
import com.example.blog.model.Roles;
import com.example.blog.model.RolesUser;
import com.example.blog.model.TUser;
import com.example.blog.service.TUserService;
import com.example.blog.core.AbstractService;
import com.example.blog.vo.TUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 陈学 on 2019-06-24 18:08:13.
 */
@Service
@Transactional
@Slf4j
public class TUserServiceImpl extends AbstractService<TUser> implements TUserService {
    @Resource
    private TUserMapper tUserMapper;
    @Resource
    private RolesMapper rolesMapper;
    @Resource
    private RolesUserMapper rolesUserMapper;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public TUser getUserByUsername(String username) {
        return tUserMapper.getUserByUsername(username);
    }

    @Override
    public int register(TUser user) {
        //先查找是否已经注册
        Condition condition = new Condition(TUser.class);
        condition.createCriteria()
                .andEqualTo("username",user.getUsername());
        List<TUser> userList = tUserMapper.selectByCondition(condition);
        if(userList != null && userList.size() > 0){
            //表示已注册
            return -1;
        }
        int result = tUserMapper.insert(user);
        return result;
    }

    @Override
    public List<Roles> isAdmin(String username) {
        return tUserMapper.isAdmin(username);
    }

    @Override
    public List<TUserVo> getAllUserInfo(String keywords) {
        List<TUser> tUserList = tUserMapper.getAllUserInfo(keywords);
        List<TUserVo> voList = new ArrayList<>();
        for (TUser tUser : tUserList){
            List<Roles> rolesList = rolesMapper.getRoleByUserId(tUser.getId());
            TUserVo tUserVo = new TUserVo();
            BeanUtils.copyProperties(tUser,tUserVo);
            tUserVo.setRegtime(sdf.format(tUser.getRegtime()));
            tUserVo.setRolesList(rolesList);
            voList.add(tUserVo);
        }
        return voList;
    }

    @Override
    public ServerResponse updateUserRoles(String rids, Integer id) {
        //先删除用户下的所有角色
        Condition condition = new Condition(RolesUser.class);
        condition.createCriteria()
                .andEqualTo("uid",id);
        rolesUserMapper.deleteByCondition(condition);
        //转换rids为list
        List<Integer> list = JSONObject.parseObject(rids,List.class);
        int reulst = rolesUserMapper.updateUserRoles(list,id);
        if(reulst ==  list.size()){
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorCodeMessage(1,"更新数据失败");
        }
    }

    @Override
    public ServerResponse getUserById(Integer id) {
        TUserVo tUserVo = new TUserVo();
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        List<Roles> rolesList = rolesMapper.getRoleByUserId(id);
        BeanUtils.copyProperties(tUser,tUserVo);
        tUserVo.setRegtime(sdf.format(tUser.getRegtime()));
        tUserVo.setRolesList(rolesList);
        return ServerResponse.createBySuccess(tUserVo);
    }

    /*
    * @Author chenxue
     * @Description 配置用户是否可见
     * @Date 2019/7/3 10:47
     * @Param [enabled, id]
     **/

    @Override
    public ServerResponse enabled(Boolean enabled, Integer id) {
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        if(tUser == null){
            return ServerResponse.createByErrorCodeMessage(1,"查询用户失败");
        }
        tUser.setEnabled(enabled);
        int result = tUserMapper.updateByPrimaryKey(tUser);
        if(result ==1){
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorCodeMessage(1,"更新失败");
        }
    }

    @Override
    public ServerResponse deleteByUserId(Integer id) {
        TUser tUser = tUserMapper.selectByPrimaryKey(id);
        tUser.setState(1);
        int result = tUserMapper.updateByPrimaryKey(tUser);
        if(result == 1){
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorCodeMessage(1,"删除用户失败");
        }
    }
}
