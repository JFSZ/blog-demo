package com.example.blog.dao;

import com.example.blog.core.Mapper;
import com.example.blog.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {
    List<Menu> getMenuByRoleId(@Param("id") Integer id);
}