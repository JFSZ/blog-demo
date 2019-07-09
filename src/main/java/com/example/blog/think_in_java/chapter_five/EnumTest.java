package com.example.blog.think_in_java.chapter_five;

import java.util.Arrays;

/**
 * @ClassName EnumTest
 * @Author chenxue
 * @Description 枚举 练习
 * @Date 2019/7/8 17:29
 **/
public enum  EnumTest {
    ONE(1,"星期一"),
    TWO(2,"星期二"),
    THREE(3,"星期三"),
    FOUR(4,"星期四"),
    FIVE(5,"星期五");
    private String value;
    private int code;
    EnumTest(int code,String value){
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
class Test {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(EnumTest.values()));
        for (EnumTest e : EnumTest.values()){
            System.out.println(e.ordinal());
        }
    }
}
