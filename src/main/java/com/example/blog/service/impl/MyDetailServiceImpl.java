/*
package com.example.blog.service.impl;

import com.example.blog.dao.RolesMapper;
import com.example.blog.model.Roles;
import com.example.blog.model.TUser;
import com.example.blog.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @ClassName MyDetailService
 * @Author chenxue
 * @Description
 * @Date 2019/6/25 16:18
 **//*

@Service
public class MyDetailServiceImpl implements UserDetailsService {
    @Autowired
    TUserService service;
    @Resource
    RolesMapper rolesMapper;
    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        TUser tUser = service.getUserByUsername(username);
        if(tUser == null){
            return new User("","",null);
        }
        List<Roles> rolesList = rolesMapper.getRoleByUserId(tUser.getId());
        for (Roles roles: rolesList){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roles.getName());
            grantedAuthorities.add(grantedAuthority);
        }
        return new User(tUser.getUsername(), tUser.getPassword(), grantedAuthorities);
    }
}
*/
