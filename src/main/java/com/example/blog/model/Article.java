package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "article")
public class Article {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "publishDate")
    private Date publishdate;

    @Column(name = "editTime")
    private Date edittime;

    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     */
    private Integer state;

    /**
     * 文章被浏览数
     */
    @Column(name = "pageView")
    private Integer pageview;

    /**
     * md文件源码
     */
    @Column(name = "mdContent")
    private String mdcontent;

    /**
     * html源码
     */
    @Column(name = "htmlContent")
    private String htmlcontent;

    private String summary;

}