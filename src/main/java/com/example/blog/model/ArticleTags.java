package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "article_tags")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ArticleTags {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章id
     */
    private Integer aid;

    /**
     * 标签id
     */
    private Integer tid;

}