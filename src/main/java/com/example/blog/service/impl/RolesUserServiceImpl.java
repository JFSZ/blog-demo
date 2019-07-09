package com.example.blog.service.impl;

import com.example.blog.dao.RolesUserMapper;
import com.example.blog.model.RolesUser;
import com.example.blog.service.RolesUserService;
import com.example.blog.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 陈学 on 2019-06-24 17:55:05.
 */
@Service
@Transactional
public class RolesUserServiceImpl extends AbstractService<RolesUser> implements RolesUserService {
    @Resource
    private RolesUserMapper rolesUserMapper;

}
