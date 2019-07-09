package com.example.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.blog.core.ServerResponse;
import com.example.blog.dao.CategoryMapper;
import com.example.blog.model.Category;
import com.example.blog.service.CategoryService;
import com.example.blog.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by 陈学 on 2019-06-24 17:54:38.
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse editCategory(Category category) {
        int result = categoryMapper.updateByPrimaryKeySelective(category);
        if(result == 1){
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorCodeMessage(1,"更新失败");
        }
    }

    @Override
    public ServerResponse deleteCategory(String ids) {
        String[] idsList;
        if(ids.contains(",")){
            idsList = ids.split(",");
        }else {
            idsList = new String[] {ids};
        }

        if(idsList == null){
            return ServerResponse.createByErrorCodeMessage(1,"删除失败");
        }
        for (String integer : idsList){
            Category category = categoryMapper.selectByPrimaryKey(integer);
            category.setState(1);
            categoryMapper.updateByPrimaryKeySelective(category);
        }
        return ServerResponse.createBySuccess();
    }
}
