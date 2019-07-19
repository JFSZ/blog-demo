package com.example.blog.dao;

import com.example.blog.core.Mapper;
import com.example.blog.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {
    List<Article> findArticleByKeyWords(@Param("userId") Integer userId,@Param("state") Integer state, @Param("keywords") String keywords);

    Integer getArticleTotal(@Param("userId")Integer userId, @Param("state")Integer state, @Param("keywords")String keywords);

    void pvStatisticsPerDay();
    List<String> getCategories(@Param("id") Integer uid);

    List<Integer> getDataStatistics(@Param("id")Integer uid);

    List<Article> getAllArticle();
}