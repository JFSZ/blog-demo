package com.example.blog.service.impl;

import com.example.blog.core.ServerResponse;
import com.example.blog.dao.RolesMapper;
import com.example.blog.model.Roles;
import com.example.blog.service.RolesService;
import com.example.blog.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by 陈学 on 2019-06-24 17:54:57.
 */
@Service
@Transactional
public class RolesServiceImpl extends AbstractService<Roles> implements RolesService {
    @Resource
    private RolesMapper rolesMapper;

    @Override
    public List<Roles> getRoleByUserId(Integer id) {
        return rolesMapper.getRoleByUserId(id);
    }
}
