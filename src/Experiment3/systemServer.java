package Experiment3;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author Fisher
 * @Date 2019/9/16 20:03
 **/


public class systemServer {

    /**
     * 数据库服务配置
     */
    private static final String serverURL = "jdbc:mysql://127.0.0.1:3306/";
    private static final String database = "test";
    private static final String serverTimeZone = "?serverTimezone=UTC";

    private static final String username = "fisher";
    private static final String password = "12345678";

    private Connection connection = null;


    /**
     * 网络服务配置
     */
    private static final int PORT = 8888;
    private ServerSocket serverSocket;

    /**
     * 构造函数
     */
    public systemServer() {
        initDB();
        initWebServer();
    }

    /**
     * 加载数据库服务
     */
    private void initDB() {
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 建立连接
            this.connection = DriverManager.getConnection(serverURL+database+serverTimeZone, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载网络服务
     */
    private void initWebServer() {
        try {
            // 建立服务器端socket
            serverSocket = new ServerSocket(PORT);
            System.out.println("建立连接：" + serverSocket.toString());

            int count = 0;

            while (true) {
                // 接受客户端连接
                Socket socket = serverSocket.accept();
                // 分配到新线程中
                Thread thread = new systemServerThread(socket, connection);
                thread.start();

                // 当前连接的IP地址
                count++;
                System.out.println("第" + count + "个连接来自IP: " + socket.getInetAddress());

            }

//            this.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放服务
     */
    private void release() {
        try {
            connection.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        systemServer server = new systemServer();
    }
}
