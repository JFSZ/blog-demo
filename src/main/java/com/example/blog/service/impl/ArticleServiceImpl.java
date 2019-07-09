package com.example.blog.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.blog.core.ServerResponse;
import com.example.blog.dao.ArticleMapper;
import com.example.blog.dao.ArticleTagsMapper;
import com.example.blog.dao.CategoryMapper;
import com.example.blog.dao.TagsMapper;
import com.example.blog.model.*;
import com.example.blog.service.ArticleService;
import com.example.blog.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by 陈学 on 2019-06-24 17:52:15.
 */
@Service
@Transactional
public class ArticleServiceImpl extends AbstractService<Article> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private TagsMapper tagsMapper;
    @Resource
    private ArticleTagsMapper articleTagsMapper;

    @Override
    public List<Article> findArticleByKeyWords(Integer state,String keywords,Integer userId) {
        return articleMapper.findArticleByKeyWords(userId,state,keywords);
    }

    @Override
    public Integer getArticleTotal(Integer state, String keywords, Integer userId) {
        return articleMapper.getArticleTotal(userId,state,keywords);
    }

    @Override
    public ServerResponse saveArticle(Article article, String tagList, TUser tUsers) {
        article.setUid(tUsers.getId());
          if(1==article.getState()){
            article.setPublishdate(new Date());
        }
        article.setEdittime(new Date());
        article.setPageview(0);
        int result = 0;
        //更新文章
        if(-1 != article.getId()){
            result = articleMapper.updateByPrimaryKeySelective(article);
        }else {
            article.setId(null);
            result = articleMapper.insert(article);
        }
        JSONArray array = JSONObject.parseArray(tagList);
        List<ArticleTags> articleTagsList = new ArrayList<>();
        //存放文章已有标签
        List<String> tagNameList = new ArrayList<>();
        Condition condition1 = new Condition(ArticleTags.class);
        condition1.createCriteria()
                .andEqualTo("aid",article.getId());
        List<ArticleTags> articleTagsList1 = articleTagsMapper.selectByCondition(condition1);
        for (ArticleTags articleTags : articleTagsList1){
            Tags tags = tagsMapper.selectByPrimaryKey(articleTags.getTid());
            tagNameList.add(tags.getTagName());
        }
        if(result >= 1){
            //保存标签
            for (int i=0;i < array.size();i++){
                String tagName = array.getString(i);
                if(tagNameList.contains(tagName)){
                    continue;
                }
                Tags tags = new Tags();
                tags.setTagName(tagName);
                Integer tagResult = tagsMapper.insert(tags);
                if(tagResult >= 1){
                    ArticleTags articleTags = new ArticleTags();
                    articleTags.setAid(article.getId());
                    articleTags.setTid(tags.getId());
                    articleTagsList.add(articleTags);
                }else {
                    return ServerResponse.createByErrorCodeMessage(1,"保存文章失败!");
                }
            }
            if(articleTagsList.size() > 0){
                int tagResult = articleTagsMapper.insertList(articleTagsList);
                if(tagResult == array.size()){
                    return ServerResponse.createBySuccess(article);
                }else {
                    return ServerResponse.createByErrorCodeMessage(1,"保存文章失败!");
                }
            }else {
                return ServerResponse.createBySuccess(article);
            }

        }else {
            return ServerResponse.createByErrorCodeMessage(1,"保存文章失败!");
        }
    }

    @Override
    public void pvStatisticsPerDay() {
        articleMapper.pvStatisticsPerDay();
    }
    /**
     * 获取最近七天的日期
     * @return
     */
    @Override
    public List<String> getCategories(Integer id) {
        return articleMapper.getCategories(id);
    }

    /**
     * 获取最近七天的数据
     * @return
     */
    @Override
    public List<Integer> getDataStatistics(Integer id) {
        return articleMapper.getDataStatistics(id);
    }
}
