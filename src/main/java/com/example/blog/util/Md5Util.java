package com.example.blog.util;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

/**
 * @ClassName Md5Util
 * @Author chenxue
 * @Description Md5加密工具
 * @Date 2019/6/26 22:44
 **/
public class Md5Util {
    public static String MD5Encode(String pwd) {
        return DigestUtils.md5DigestAsHex(pwd.getBytes());
    }
}
