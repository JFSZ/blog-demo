package com.example.blog.service;
import com.example.blog.core.ServerResponse;
import com.example.blog.model.Roles;
import com.example.blog.model.TUser;
import com.example.blog.core.Service;
import com.example.blog.vo.TUserVo;

import java.util.List;


/**
 * Created by 陈学 on 2019-06-24 18:08:13.
 */
public interface TUserService extends Service<TUser> {

    TUser getUserByUsername(String username);

    int register(TUser user);

    List<Roles> isAdmin(String username);

    List<TUserVo> getAllUserInfo(String keywords);

    ServerResponse updateUserRoles(String rids, Integer id);

    ServerResponse getUserById(Integer id);

    ServerResponse enabled(Boolean enabled, Integer id);

    ServerResponse deleteByUserId(Integer id);
}
