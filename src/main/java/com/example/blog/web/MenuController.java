package com.example.blog.web;
import com.example.blog.core.ServerResponse;
import com.example.blog.model.Menu;
import com.example.blog.service.MenuService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/*
* @Author  陈学
 * @Description
 * @Date 2019-07-04 15:09:25
 **/

@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {
    @Resource
    private MenuService menuService;

    @ApiOperation(value = "新增",notes = "新增")
    @PostMapping("/add")
    public ServerResponse add(Menu menu) {
        menuService.save(menu);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "删除",notes = "删除")
    @PostMapping("/delete")
    public ServerResponse delete(@RequestParam Integer id) {
        menuService.deleteById(id);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "更新",notes = "更新")
    @PostMapping("/update")
    public ServerResponse update(Menu menu) {
        menuService.update(menu);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "查询详情",notes = "查询详情")
    @PostMapping("/detail")
    public ServerResponse detail(@RequestParam Integer id) {
        Menu menu = menuService.findById(id);
        return ServerResponse.createBySuccess(menu);
    }

    @ApiOperation(value = "查询列表",notes = "查询列表")
    @PostMapping("/list")
    public ServerResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Menu> list = menuService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
