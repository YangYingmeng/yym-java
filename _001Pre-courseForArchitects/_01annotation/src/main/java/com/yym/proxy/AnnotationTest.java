package com.yym.proxy;


/*
*     JDK内置注解:
*         @Override: 重写方法, 当重写的方法由语法问题时, 会报错
*         @Deprecated: 表名一个过期方法(类 构造器等), 废弃方法
*         @SuppressWarnings: 抑制编译器警告
* */
public class AnnotationTest {
    public static void main(String[] args) {

    }
}

@MyAnnotation(value = {"123", "456", "789"})
class Person {

}

@MyAnnotation2()
class Person2 {

}
