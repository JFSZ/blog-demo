package com.example.blog.think_in_java.chapter_five;

import java.util.Arrays;
import java.util.Random;

/**
 * @ClassName StaticTest
 * @Author chenxue
 * @Description static 练习
 * @Date 2019/7/8 15:18
 **/
public class StaticTest {
    private static String name = "TOM";
    private static String age;
    static {
        age = "22";
    }
    void init(){
        System.out.println(name);
        System.out.println(age);
    }

    public static void main(String[] args) {
        /*System.out.println(name);
        System.out.println(age);
        StaticTest staticTest = new StaticTest();
        staticTest.init();*/

        int[] a = new int[new Random(1).nextInt(20)];
        System.out.println(a.length);
        System.out.println(Arrays.toString(a));
    }
}
