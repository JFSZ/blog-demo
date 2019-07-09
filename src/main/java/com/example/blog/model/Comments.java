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
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章id
     */
    private Integer aid;

    /**
     * 发布日期
     */
    @Column(name = "publishDate")
    private Date publishdate;

    /**
     * -1表示正常回复，其他值表示是评论的回复
     */
    @Column(name = "parentId")
    private Integer parentid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 评论内容
     */
    private String content;

   }