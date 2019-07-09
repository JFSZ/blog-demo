package com.example.blog.dao;

import com.example.blog.core.Mapper;
import com.example.blog.model.Roles;
import com.example.blog.model.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper extends Mapper<TUser> {
    TUser getUserByUsername(@Param("username") String username);

    List<Roles> isAdmin(@Param("username")String username);

    List<TUser> getAllUserInfo(@Param("keywords") String keywords);
}