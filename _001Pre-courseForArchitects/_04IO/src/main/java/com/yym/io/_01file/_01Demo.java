package com.yym.io._01file;

import java.io.File;
import java.io.IOException;

/**
 * @Description: File类基本用法
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-03 16:11
 */
public class _01Demo {


    static File file = new File("E:\\05yymLearn\\yym-java\\_001Pre-courseForArchitects\\_04IO\\src\\main\\resources\\test.txt");
    static File file1 = new File("E:\\05yymLearn\\yym-java\\_001Pre-courseForArchitects\\_04IO\\src\\main\\resources\\", "test.txt");
    static File file2 = new File("_001Pre-courseForArchitects/_04IO/src/main/resources/test.txt");
    static File file3 = new File("test1.txt");
    static File directory = new File("_001Pre-courseForArchitects/_04IO/src/main/resources/test");

    // 1. 创建file
    private static void fileConstruct() {
        // 通过路径名创建
        // 通过父路径 + 子路径创建
        System.out.println("file: " + file);
        System.out.println("file1: " + file1);
    }

    // 2. api
    public static void fileApiTest() throws IOException {
        System.out.println("file的绝对路径: " + file.getAbsolutePath());
        System.out.println("file的相对路径: " + file2.getPath());
        System.out.println("file的名称: " + file.getName());
        System.out.println("file的长度: " + file.length());
        System.out.println("file是否存在: " + file3.exists());
        System.out.println("file是文件还是文件目录: " + (directory.isDirectory()? "是文件夹" : "是文件"));
        System.out.println("file是文件还是文件目录: " + (file2.isFile() ? "是文件" : "是文件夹"));
        // 创建一个新的空文件 若当前文件不存在 directory 已存在
        System.out.println("new File: " + directory.createNewFile());
        File file4 = new File("_001Pre-courseForArchitects/_04IO/src/main/resources/test1");
        System.out.println("new File: " + file4.createNewFile());
        // 创建目录
        System.out.println("file4.mkdir(): " + file4.mkdir());
        // 删除文件或目录
        file4.delete();
    }

    public static void main(String[] args) throws IOException {
        // fileConstruct();
        fileApiTest();
    }
}
