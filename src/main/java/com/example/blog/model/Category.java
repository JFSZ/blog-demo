package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类别名称
     */
    @Column(name = "cateName")
    private String catename;

    /**
     * '0正常 1 删除'
     */
    @Column(name = "state")
    private Integer state;

    /**
     * 创建时间
     */
    private Date date;

}