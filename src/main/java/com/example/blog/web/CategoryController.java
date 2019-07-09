package com.example.blog.web;
import com.example.blog.core.ServerResponse;
import com.example.blog.model.Category;
import com.example.blog.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/*
* @Author  陈学
 * @Description
 * @Date 2019-06-24 17:54:38
 **/

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @ApiOperation(value = "新增",notes = "新增")
    @PostMapping("/add")
    public ServerResponse add(Category category) {
        categoryService.save(category);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "删除",notes = "删除")
    @PostMapping("/delete")
    public ServerResponse delete(@RequestParam Integer id) {
        categoryService.deleteById(id);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "更新",notes = "更新")
    @PostMapping("/update")
    public ServerResponse update(Category category) {
        categoryService.update(category);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "查询详情",notes = "查询详情")
    @PostMapping("/detail")
    public ServerResponse detail(@RequestParam Integer id) {
        Category category = categoryService.findById(id);
        return ServerResponse.createBySuccess(category);
    }

    @ApiOperation(value = "查询列表",notes = "查询列表")
    @PostMapping("/list")
    public ServerResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Category> list = categoryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }
    @ApiOperation(value = "获取所有分类",notes = "获取所有分类")
    @PostMapping("/getAllCategory")
    public ServerResponse getAllCategory(){
        Condition condition = new Condition(Category.class);
        condition.createCriteria()
                .andEqualTo("state",0);
        List<Category> category = categoryService.findByCondition(condition);
        return ServerResponse.createBySuccess(category);
    }
}
