package com.example.blog.dao;

import com.example.blog.core.Mapper;
import com.example.blog.model.Roles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolesMapper extends Mapper<Roles> {
    public List<Roles> getRoleByUserId(@Param("userId") Integer userId);
}