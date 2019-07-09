package com.example.blog.vo;

import com.example.blog.model.Article;
import com.example.blog.model.ArticleTags;
import com.example.blog.model.Tags;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleVo {
    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类别id
     */
    private Integer cid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 发表日期
     */
    private String publishdate;

    private String edittime;

    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     */
    private Integer state;

    /**
     * 文章被浏览数
     */
    private Integer pageview;

    /**
     * md文件源码
     */
    private String mdcontent;

    /**
     * html源码
     */
    private String htmlcontent;

    private String summary;
    //昵称
    private String nickname;
    //分类名称
    private String cateName;
    //标签集合
    private List<Tags> tagsList;
    //文章总数
    private Integer totalCount;

}