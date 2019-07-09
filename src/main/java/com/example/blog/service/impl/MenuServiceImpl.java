package com.example.blog.service.impl;

import com.example.blog.dao.MenuMapper;
import com.example.blog.model.Menu;
import com.example.blog.service.MenuService;
import com.example.blog.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by 陈学 on 2019-07-04 15:09:25.
 */
@Service
@Transactional
public class MenuServiceImpl extends AbstractService<Menu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByRoleId(Integer id) {
        return menuMapper.getMenuByRoleId(id);
    }
}
