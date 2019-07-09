package com.example.blog.dao;

import com.example.blog.core.Mapper;
import com.example.blog.model.RolesUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolesUserMapper extends Mapper<RolesUser> {
    Integer updateUserRoles(@Param("rids") List rids, @Param("id") Integer id);
}