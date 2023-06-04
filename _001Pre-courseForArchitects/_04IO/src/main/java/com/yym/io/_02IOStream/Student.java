package com.yym.io._02IOStream;

import java.io.Serializable;

/**
 * @Description: 序列化对象
 *                  1. 序列化的对象实现 Serializable 接口
 *                  2. 如果有属性不需要序列化 可以使用 transient 修饰
 *                  3. 序列化版本号: 反序列化时会进行版本号校验, 不符报错
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-04 20:46
 */
public class Student implements Serializable {

    //固定一个值，什么值都可以
    private static final long serialVersionUID = 1L;


    private String name;
    private String address;
    private transient Integer age;

    public Student() {

    }

    public Student(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
