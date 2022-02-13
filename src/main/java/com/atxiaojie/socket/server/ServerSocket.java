package com.atxiaojie.socket.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName: ServerSocket
 * @Description: 服务器socket
 * @author: zhouxiaojie
 * @date: 2021/11/5 21:44
 * @Version: V1.0.0
 */
public class ServerSocket {
    //客户端发送信息
    public static void main(String[] args) {
        OutputStream out = null;
        Socket socket = null;
        try {
            //绑定到本地端口
            socket = new Socket("192.168.1.2", 8090);
            //发送消息
            while (true) {
                System.out.println("==========");
                out = socket.getOutputStream();
                //输入文字，从控制台输入
                Scanner san = new Scanner(System.in);
                String str = san.next();
                //System.out.println("我:" + str);
                out.write(str.getBytes());
                out.flush();
                //接收信息
                InputStream in = socket.getInputStream();
                //获取输入流里面数据并存储数据
                byte[] b = new byte[1024];
                StringBuffer sb = new StringBuffer();
                String s;
                if (in.read(b) != -1) {
                    s = new String(b);
                    System.out.println(s);
                    sb.append(s);
                }
                System.out.println("来自服务器的数据:" + sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
