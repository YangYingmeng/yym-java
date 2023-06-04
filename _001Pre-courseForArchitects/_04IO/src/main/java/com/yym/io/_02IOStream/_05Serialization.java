package com.yym.io._02IOStream;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @Description: 序列化
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-04 20:45
 */
@Slf4j
public class _05Serialization {

    private static final String filePath = "_001Pre-courseForArchitects/_04IO/src/main/resources/serializable.obj";

    // 1. 将对象序列化到文件中
    private static void serialization(Student s) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(s);
        } catch (IOException ex) {
            log.error("序列化报错: ", ex);
        }
    }

    // 2. 将文件中的对象 反序列化
    private static void deserialization() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Student s = (Student) ois.readObject();
            System.out.println("student: " + s);
            // 文件中只有一个学生对象, 再次反序列化报错
            Object o = ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            log.error("反序列化报错: ", ex);
        }
    }

    public static void main(String[] args) {
        serialization(new Student(11, "test", "address"));
        deserialization();
    }
}
