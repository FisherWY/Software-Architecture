package Experiment3;

import com.sun.istack.internal.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author Fisher
 * @Date 2019/9/18 19:54
 **/


public class systemServerThread extends Thread {

    // 数据库连接
    private Connection connection = null;
    private PreparedStatement statement = null;

    // 网络socket
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;
    private Socket socket = null;

    // 构造函数，接受新客户端的socket，数据库连接connection
    public systemServerThread(Socket socket, Connection connection) {
        try {
            this.socket = socket;
            this.connection = connection;
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            // 查询结果
            while (true) {
                // 读数据
                String user_name = inputStream.readUTF();
                System.out.println("查询的用户名: " + user_name);

                if ("!end".equals(user_name)) break;

                // 查询并返回
                ResultSet res = this.select(user_name);
                String data = "";
                while (res.next()) {
                    String s = "UUID：" + res.getString(1) + "    User name：" + res.getString(2) + "    Department：" + res.getString(3) + "\n";
                    System.out.println("查到结果 -> " + s);
                    data += s;
                }
                outputStream.writeUTF(data + "\n查询完成!");
                outputStream.flush();
            }

            // socket会话结束，关闭连接
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
            if (socket != null) socket.close();

            if (statement != null) statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param User_name
     * @return 查询结果
     */
    private ResultSet select(@NotNull String User_name) {
        try {
            String sql = "select * from user where User_name = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, User_name);

            ResultSet result = statement.executeQuery();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
