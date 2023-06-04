package com.yym.io._02IOStream;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Description: 转换流 字节流和字符流的桥梁, 可以读取字节并使用指定的字符集将其解码为字符
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-04 20:30
 */

@Slf4j
public class _04ConversionStream {

    private static final String filePath = "_001Pre-courseForArchitects/_04IO/src/main/resources/testBuffer.txt";

    // 1. 指定编码读取
    private static void inputStreamReader() {
        try (InputStreamReader isr = new InputStreamReader(Files.newInputStream(Paths.get(filePath)), "GBK");){
            int c;
            while ((c = isr.read()) != -1) {
                log.info("文件中数据: {}", (char) c);
            }
        } catch (IOException ex) {
            log.error("指定编码读取异常: ", ex);
        }
    }

    // 2. 指定编码写出
    private static void outputStreamWrite() {
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), "GBK")) {
            osw.write("指定编码写出");
        } catch (IOException ex) {
            log.error("指定编码写出取异常: ", ex);
        }
    }

    public static void main(String[] args) {

    }
}
