package Experiment3;

import java.io.*;
import java.net.Socket;

/**
 * @Author Fisher
 * @Date 2019/9/16 16:36
 **/


public class tcpServerThread extends Thread {

    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;
    private Socket socket = null;

    // 构造函数，接受新客户端的socket
    public tcpServerThread(Socket socket) {
        try {
            this.socket = socket;
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
            while (true) {
                // 获取输入流
                String receive = inputStream.readUTF();
                // 判断是否满足退出条件
                if ("end".equals(receive)) break;
                // 输出接收到的字符串
                System.out.println("From Client: " + receive);
                // 向client发送回传数据
                outputStream.writeUTF("Get: " + receive);
                outputStream.flush();
            }

            // 结束连接
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
