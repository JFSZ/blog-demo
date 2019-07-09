package com.example.blog.think_in_java.chapter_five;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName FinalizeTest
 * @Author chenxue
 * @Description finalize 练习
 * @Date 2019/7/8 13:56
 **/
public class FinalizeTest {
    public static void main(String[] args) {
        Book book = new Book();
        User user = new User(book);
        book.recycleBy(user);
        book = null;
        System.gc();
    }
}
class User {
    private boolean flag;
    private Book book;
    User(Book book){
        this.book = book;
    }
    User(boolean flag){
        this.flag = flag;
    }
    @Override
    protected void finalize() throws Throwable {
        System.out.println("User 执行回收操作...");
        super.finalize();
    }
}
class Book {
    private User user;
    Book(){

    }
    User recycleBy(User user){
        return user;
    }
    @Override
    protected void finalize() throws Throwable {
        System.out.println("book 执行回收操作");
        super.finalize();
    }
}
