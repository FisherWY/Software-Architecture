package Experiment3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @Author Fisher
 * @Date 2019/9/16 16:55
 **/


public class tcpClientThread extends Thread {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 8888;
    private int NO;

    public tcpClientThread(int NO) {
        this.NO = NO;
    }

    @Override
    public void run() {
        super.run();

        try {
            // 建立连接
            Socket socket = new Socket(ADDRESS, PORT);
            System.out.println("第" + NO + "个socket建立：" + socket);

            // 建立IO流
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // 发送消息
            for (int i=0; i<10; i++) {
                // 向服务器发送消息
                outputStream.writeUTF("NO" + NO + " -> 消息: " + i);
                outputStream.flush();

                // 接受来自服务器的消息
                System.out.println("NO" + NO + " -> 来自服务器端的消息: " + inputStream.readUTF());

                // 线程休眠，做到多个线程同时通信的效果
                sleep((int)(Math.random()*1000));
            }

            // 发送终止标志
            outputStream.writeUTF("!end");
            outputStream.flush();

            // 关闭连接
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Thread[] clientThreads = new tcpClientThread[5];
        for (int i=0; i<5; i++) {
            clientThreads[i] = new tcpClientThread(i);
            clientThreads[i].start();
        }
    }
}
