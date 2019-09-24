package Experiment3;

import java.io.*;
import java.net.*;
public class tcpServer {

    public static final int PORT=8888;

    public static void main(String[] args) throws IOException{
        //建立ServerSocket
        ServerSocket s=new ServerSocket(PORT);
        System.out.println("ServerSocket:"+s);
        try{
            /*程序阻塞,等待连接。即直到有一个客户请求到达,程序方能继续执行*/
            Socket ss=s.accept();
            System.out.println("Socket accept:"+ss);
            try {
                //连接成功，建立相应的I/O数据流
                DataInputStream dis=new DataInputStream(ss.getInputStream());
                DataOutputStream dos=new DataOutputStream(ss.getOutputStream());
                //在循环中，与客户机通信
                while(true){
                    String str=dis.readUTF();	//从客户机中读数据
                    if(str.equals("end"))break;	//当读到end时，程序终止
                    System.out.println(str);
                    dos.writeUTF("Echoing:"+str);	//向客户机中写数据
                }
                dos.close();
                dis.close();
            }finally{
                ss.close();
            }
        }finally{
            s.close();
        }
    }
}
