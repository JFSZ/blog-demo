package com.example.blog.service;
import com.example.blog.model.Menu;
import com.example.blog.core.Service;

import java.util.List;


/**
 * Created by 陈学 on 2019-07-04 15:09:25.
 */
public interface MenuService extends Service<Menu> {

    List<Menu> getMenuByRoleId(Integer id);
}
