package com.yym.nettyAction.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: 代码清单 1-1 阻塞 I/O 示例
 *                  该方案有2处阻塞, 并且同时只能处理一个连接, 要管理多个客户端需要为每个新的客户端创建一个
 *                  新的Thread;
 *                  缺点:
 *                      1. 任何时候都可能有大量的线程处于休眠状态, 等待数据的输入输出, 资源的浪费
 *                      2. 需要为每个线程的调用栈分配内存
 *                      3. 线程的上下文切换带来的开销无法避免
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-28 23:00
 */
public class _01BlockingIoExample {

    public void server(int portNumber) throws IOException {
        // 创建一个新的 ServerSocket, 用以监听指定端口上的连接请求
        ServerSocket serverSocket = new ServerSocket(portNumber);
        // 对accept()方法的调用将被阻塞, 知道一个连接建立
        Socket clientSocket = serverSocket.accept();
        // 这些流对象都派生于Socket的输入输出流
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;
        // 处理循环开始 readLine()方法也被阻塞, 直到字符串被读取
        while ((request = in.readLine()) != null ) {
            if ("Done".equals(request)) {
                break;
            }
            // 请求被传递给服务器的处理方法
            response = processRequest(request);
            // 服务器的响应被发送给了客户端
            out.print(response);
            // 继续处理循环
        }
    }

    private String processRequest(String request){
        return "Processed";
    }
}
