package com.yym.io._02IOStream;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Description: 文件的字符输入流和输出流
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-03 21:11
 */
@Slf4j
public class _02FileWriteReadStream {

    private static final String filePath = "_001Pre-courseForArchitects/_04IO/src/main/resources/test.txt";

    // 1. FileReader  从文件中读取字符
    private static void readFromFile() {
        try (FileReader fileReader = new FileReader(filePath)) {
            int len = 0;
            char[] charBuff = new char[1024];
            while ((len = fileReader.read(charBuff)) != -1) {
                log.info("文件内容: {}", new String(charBuff, 0, len));
            }
        } catch (IOException ex) {
            log.error("从文件中读取字符异常: ", ex);
        }
    }

    // 2. FileWrite  字符输出流
    private static void writeToFile() {

        // public FileWriter(String fileName, boolean append) true在原数据后追加, false清除原数据
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(97);
            // 关闭流后无法继续使用 可以使用刷新方法
            fileWriter.flush();
            fileWriter.write(" fileWrite");
            char[] temp = {'a', 'b'};
            fileWriter.write(temp, 1, 1);
        }
         catch (IOException ex) {
            log.error("向文件中输入数据异常: ", ex);
        }

    }

    public static void main(String[] args) throws IOException {
        writeToFile();
    }
}
