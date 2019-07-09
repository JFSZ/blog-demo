package com.example.blog.service;
import com.example.blog.core.ServerResponse;
import com.example.blog.model.Category;
import com.example.blog.core.Service;


/**
 * Created by 陈学 on 2019-06-24 17:54:38.
 */
public interface CategoryService extends Service<Category> {

    ServerResponse editCategory(Category category);

    ServerResponse deleteCategory(String ids);
}
