package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TUser {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否可见
     */
    private Boolean enabled;

    /**
     * 用户状态 0正常 1 删除
     */
    private Integer state;

    /**
     * y邮箱
     */
    private String email;

    /**
     * 头像地址
     */
    private String userface;

    /**
     * 注册时间
     */
    @Column(name = "regTime")
    private Date regtime;

}