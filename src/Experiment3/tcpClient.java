package Experiment3;

import java.io.*;
import java.net.*;

public class tcpClient {

    public static void main(String[] args) throws IOException{

        //建立Socket，服务器在本机的8888端口处进行“侦听”
        Socket ss=new Socket("127.0.0.1",8888);
        System.out.println("Socket:"+ss);

        try{
            //套接字建立成功，建立I/O流进行通信
            DataInputStream dis=new DataInputStream(ss.getInputStream());
            DataOutputStream dos=new DataOutputStream(ss.getOutputStream());

            for(int i=0;i<6;i++){
                dos.writeUTF("测试:"+i);	//向服务器发数据
                dos.flush();			//刷新输出缓冲区，以便立即发送
                System.out.println(dis.readUTF());	//将从服务器接收的数据输出
            }

            dos.writeUTF("end");		//向服务器发送终止标志
            dos.flush();				//刷新输出缓冲区，以便立即发送
            dos.close();
            dis.close();

        }finally{
            ss.close();
        }
    }
}
