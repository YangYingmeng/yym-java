package com.yym.io._02IOStream;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Description: Buffer是对字节输入输出流 以及 字符输入输出流的管理
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-03 21:34
 */
@Slf4j
public class _03Buffer {

    private static final String filePath = "_001Pre-courseForArchitects/_04IO/src/main/resources/testBuffer.txt";
    private static final Path path = Paths.get(filePath);

    // 1. 字节缓冲输入 输出流
    private static void byteBuffer() {

        try (// 在原数据的基础上追加数据, 默认是覆盖原数据
             BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(path, StandardOpenOption.APPEND));
             BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path))
        ) {
            // 写入
            bos.write("byteBuffer".getBytes());
            // 读取
            byte[] readByte = new byte[1022 * 2];
            int len = 0;
            while ((len = bis.read(readByte)) != -1) {
                log.info("文件数据: {}", new String(readByte, 0, len));
            }
        } catch (IOException ex) {
            log.error("字节缓冲流异常: ", ex);
        }
    }

    // 2. 字符缓冲输入 输出流
    private static void charBuffer() {

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
                BufferedReader br = new BufferedReader(new FileReader(filePath))
        ) {
            // 写数据
            bw.write("BufferedWriter");
            // 换行
            bw.newLine();
            // 如果不刷新 本次写入数据无法及时被读取
            bw.flush();
            // 读取数据
            StringBuilder builder = new StringBuilder("");
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }
            log.info("文件数据: {}", builder);
        } catch (IOException ex) {
            log.error("字符缓冲流异常: ", ex);
        }
    }

    public static void main(String[] args) throws IOException {
        charBuffer();
    }
}
