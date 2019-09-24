package Experiment3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Fisher
 * @Date 2019/9/16 16:23
 **/


public class tcpServerNginx {

    private static final int PORT = 8888;

    private void startServer() {
        try {
            // 建立服务器端socket
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("建立连接：" + serverSocket.toString());

            int count = 0;

            // 通过死循环来不断接受各个客户端的连接
            while (true) {
                // 接受新的客户端连接
                Socket socket = serverSocket.accept();
                // 分配到新的线程中
                Thread thread = new tcpServerThread(socket);
                thread.start();

                // 当前连接的IP地址
                count++;
                System.out.println("第" + count + "个连接来自IP: " + socket.getInetAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        tcpServerNginx nginx = new tcpServerNginx();
        nginx.startServer();
    }
}
