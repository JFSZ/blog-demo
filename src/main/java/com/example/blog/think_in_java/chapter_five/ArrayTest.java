package com.example.blog.think_in_java.chapter_five;

import java.util.Arrays;

/**
 * @ClassName ArrayList
 * @Author chenxue
 * @Description 数组练习
 * @Date 2019/7/8 16:33
 **/
public class ArrayTest {
    public static void main(String[] args) {
        /*//初始化数组的三种方式
        //1静态初始化
        String[] array = new String[]{"1","2","3","4","5"};
        System.out.println(Arrays.toString(array));
        //2静态初始化
        String[] array1 = {"1","2","3","4",};
        System.out.println(Arrays.toString(array1));
        //3动态初始化
        String[] array2 = new String [4];
        array1 = new String[]{"1","2","3","4",};
        System.out.println(Arrays.toString(array1));*/
        pringArray(new Integer[]{1,2,3,4,5});
    }
    static void pringArray(Object ... args){
        for (Object o : args){
            System.out.println("Object" + o);
        }
    }
}
