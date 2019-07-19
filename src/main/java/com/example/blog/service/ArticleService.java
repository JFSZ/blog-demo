package com.example.blog.service;
import com.example.blog.core.ServerResponse;
import com.example.blog.model.Article;
import com.example.blog.core.Service;
import com.example.blog.model.TUser;

import java.util.List;


/**
 * Created by 陈学 on 2019-06-24 17:52:15.
 */
public interface ArticleService extends Service<Article> {

    List<Article> findArticleByKeyWords(Integer state,String keywords,Integer userId);

    Integer getArticleTotal(Integer state, String keywords, Integer id);

    ServerResponse saveArticle(Article article, String tagList, TUser tUsers);

    void pvStatisticsPerDay();

    List<String> getCategories(Integer id);

    List<Integer> getDataStatistics(Integer id);

    List<Article> getAllArticle();
}
