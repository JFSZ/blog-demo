package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "menu")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单父类id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 菜单类型;资源类型,0:目录;1:菜单;2:按钮
     */
    private Integer type;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 状态 0 正常 1 删除
     */
    private Boolean state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}