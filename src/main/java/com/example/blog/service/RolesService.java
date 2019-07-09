package com.example.blog.service;
import com.example.blog.core.ServerResponse;
import com.example.blog.model.Roles;
import com.example.blog.core.Service;

import java.util.List;


/**
 * Created by 陈学 on 2019-06-24 17:54:57.
 */
public interface RolesService extends Service<Roles> {

    List<Roles> getRoleByUserId(Integer id);
}
