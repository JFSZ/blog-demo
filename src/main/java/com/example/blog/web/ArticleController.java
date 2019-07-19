package com.example.blog.web;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.blog.core.ServerResponse;
import com.example.blog.model.*;
import com.example.blog.service.*;
import com.example.blog.util.StringTools;
import com.example.blog.vo.ArticleVo;
import com.example.blog.vo.ChartsVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import org.apache.commons.io.IOUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
* @Author  陈学
 * @Description
 * @Date 2019-06-24 17:52:15
 **/

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private TUserService tUserService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private ArticleTagsService articleTagsService;
    @Resource
    private TagsService tagsService;

    @Value("${file.uploadPath}")
    private String upLoadPath;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @ApiOperation(value = "新增",notes = "新增")
    @PostMapping("/add")
    public ServerResponse add(Article article) {
        articleService.save(article);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "删除",notes = "删除")
    @PostMapping("/delete")
    public ServerResponse delete(@RequestParam Integer id) {
        articleService.deleteById(id);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "更新",notes = "更新")
    @PostMapping("/update")
    public ServerResponse update(Article article) {
        articleService.update(article);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "查询详情",notes = "查询详情")
    @PostMapping("/detail")
    public ServerResponse detail(@RequestParam Integer id) {
        Article article = articleService.findById(id);
        return ServerResponse.createBySuccess(article);
    }

    @ApiOperation(value = "查询列表",notes = "查询列表")
    @PostMapping("/list")
    public ServerResponse list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Article> list = articleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @ApiOperation(value = "根据关键字查询所有列表",notes = "根据关键字查询所有列表")
    @PostMapping("/all")
    public ServerResponse all(@RequestParam(defaultValue = "1") Integer state, @RequestParam String keywords, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "6") Integer size) {
        PageHelper.startPage(page, size);
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if( StringTools.isEmpty(username)){
            return ServerResponse.createByErrorCodeMessage(2,"获取登录人信息失败,请重新登录!");
        }
        TUser tUsers = tUserService.getUserByUsername(username);
        List<Article> list = articleService.findArticleByKeyWords(state,keywords,tUsers.getId());
        List<ArticleVo> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM:dd HH:mm:ss");
        for (Article article : list){
            ArticleVo articleVo = new ArticleVo();
            BeanUtils.copyProperties(article,articleVo);
            articleVo.setEdittime(sdf.format(article.getEdittime()));
            //
            Integer uid = article.getUid();
            Integer cid = article.getCid();
            TUser tUser1 = tUserService.findById(uid);
            Category category = categoryService.findById(cid);
            articleVo.setNickname(tUser1.getNickname());
            articleVo.setCateName(category.getCatename());
            voList.add(articleVo);
        }
        //查询文章总数

        Integer total = articleService.getArticleTotal(state,keywords,tUsers.getId());
        List<HashMap<String,Object>> resultList = new ArrayList<>();
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("voList",voList);
        resultMap.put("total",0);
        resultList.add(resultMap);
        PageInfo pageInfo = new PageInfo(resultList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @ApiOperation(value = "查询文章详情",notes = "查询文章详情")
    @PostMapping("/getArticleInfo")
    public ServerResponse getArticleInfo(@RequestParam Integer id) {
        Article article = articleService.findById(id);
        ArticleVo articleVo = new ArticleVo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM:dd HH:mm:ss");
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setEdittime(sdf.format(article.getEdittime()));
        //查询作者信息
        TUser tUser = tUserService.findById(article.getUid());
        if(tUser == null){
            return ServerResponse.createByErrorCodeMessage(2,"获取登录人信息失败,请重新登录!");
        }
        articleVo.setNickname(tUser.getNickname());
        Category category = categoryService.findById(article.getCid());
        articleVo.setCateName(category.getCatename());
        //查询文章标签
        Condition condition = new Condition(ArticleTags.class);
        condition.createCriteria()
                .andEqualTo("aid",article.getId());
        List<ArticleTags> articleTagsList = articleTagsService.findByCondition(condition);
        List<Tags> tagsList = new ArrayList<>();
        for (ArticleTags articleTags : articleTagsList){
            Tags tags = tagsService.findById(articleTags.getTid());
            tagsList.add(tags);
        }
        articleVo.setTagsList(tagsList);
        return ServerResponse.createBySuccess(articleVo);
    }

    /*
    * @Author chenxue
     * @Description 删除
     * @Date 2019/6/28 11:32
     * @Param []
     * @return com.example.blog.core.ServerResponse
     **/

    @ApiOperation(value = "删除",notes = "删除")
    @PostMapping("/dustbin")
    public ServerResponse dustbin(@RequestParam String ids,@RequestParam Integer state){
        JSONArray jsonArray = JSONObject.parseArray(ids);
        for (int i = 0; i< jsonArray.size(); i++){
            Integer id = jsonArray.getInteger(i);
            Article article = articleService.findById(id);
            if(2 == state){
                article.setState(2);
            }else {
                article.setState(2);
            }
            article.setEdittime(new Date());
            articleService.update(article);
        }
        return ServerResponse.createBySuccess();
    }

    /*
    * @Author chenxue
     * @Description 保存文章
     * @Date 2019/6/28 15:55
     * @Param []
     * @return com.example.blog.core.ServerResponse
     **/

    @ApiOperation(value = "保存文章",notes = "保存文章")
    @PostMapping("/save")
    public ServerResponse save(Article article,@RequestParam String tagList){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if( StringTools.isEmpty(username)){
            return ServerResponse.createByErrorCodeMessage(2,"获取登录人信息失败,请重新登录!");
        }
        TUser tUser = tUserService.getUserByUsername(username);
        return articleService.saveArticle(article,tagList,tUser);
    }


    @ApiOperation(value = "上传图片",notes = "上传图片")
    @PostMapping("/uploadImg")
    public ServerResponse uploadImg(HttpServletRequest request, MultipartFile file){
        StringBuffer url = new StringBuffer();
        String filePath = "/blogimg/" + sdf.format(new Date());
        //String imgFolderPath = request.getServletContext().getRealPath(filePath);
        File file1 = new File(upLoadPath,filePath);
        if(!file1.exists()){
            file1.mkdirs();
        }
        url.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + file.getOriginalFilename().replaceAll(" ", "");
        try{
            IOUtils.write(file.getBytes(),new FileOutputStream(new File(file1,imgName)));
            url.append("/").append(imgName);
            return ServerResponse.createBySuccess(url.toString());
        }catch (IOException e){
            return ServerResponse.createByErrorCodeMessage(1,"上传图片失败");
        }
    }


    @RequestMapping("/dataStatistics")
    public ServerResponse dataStatistics() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if( StringTools.isEmpty(username)){
            return ServerResponse.createByErrorCodeMessage(2,"获取登录人信息失败,请重新登录!");
        }
        TUser tUser = tUserService.getUserByUsername(username);
        List<String> categories = articleService.getCategories(tUser.getId());
        List<Integer> dataStatistics = articleService.getDataStatistics(tUser.getId());
        ChartsVo chartsVo = new ChartsVo();
        chartsVo.setCategories(categories);
        chartsVo.setDs(dataStatistics);
        return ServerResponse.createBySuccess(chartsVo);
    }
    @PostMapping("/getAllArticle")
    public ServerResponse getAllArticle(){
        List<Article> list = articleService.getAllArticle();
        return null;
    }
}
