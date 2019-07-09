package com.example.blog.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName StringUtils
 * @Author chenxue
 * @Description 字符串工具类
 * @Date 2019/6/25 17:10
 **/
public class StringTools {
    public static boolean isEmpty(String str){
        try{
            return str == null || str.length() <= 0;
        }catch (Exception e){
            return true;
        }
    }
    public static String stringof(Object o){
        if(o == null || "".equals(o)){
            return "";
        }else {
            try {
                return String.valueOf(o);
            }catch (Exception e){
                return "";
            }
        }
    }
}
