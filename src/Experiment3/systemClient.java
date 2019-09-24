package Experiment3;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @Author Fisher
 * @Date 2019/9/16 21:00
 **/


public class systemClient  {

    // Socket
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 8888;

    private Socket socket = null;
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;

    // 界面按钮
    private JFrame window;
    private JPanel jPanel;
    private JButton button_connect;
    private JLabel jLabel;
    private JTextField jTextField;
    private JButton button_send;
    private JTextArea jTextArea;
    private JScrollPane jScrollPane;

    // 连接状态
    private boolean connected = false;

    public systemClient() {
        createGUI();

    }

    /**
     * 创建窗口
     */
    private void createGUI() {
        // 设置JFrame窗口
        window = new JFrame("Application");
        window.setSize(600, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (connected) {
                    release();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        // 设置画板
        jPanel = new JPanel();
        jPanel.setLayout(null);

        // 连接服务器按钮
        button_connect = new JButton("连接服务器");
        button_connect.setBounds(10,20,100,30);
        button_connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 连接或断开服务器
                if (!connected) {
                    connect();
                } else {
                    release();
                }
            }
        });
        jPanel.add(button_connect);

        // 输入学号Label
        jLabel = new JLabel("输入学号");
        jLabel.setBounds(150,20,75,30);
        jPanel.add(jLabel);

        // 输入框
        jTextField = new JTextField(15);
        jTextField.setBounds(230,20,150,30);
        jPanel.add(jTextField);

        // 发送按钮
        button_send = new JButton("发送");
        button_send.setBounds(400,20,75,30);
        button_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 查询结果
                jTextArea.setText("");
                getResult(jTextField.getText());
                jTextField.setText("");
                jPanel.repaint();
            }
        });
        jPanel.add(button_send);

        // 结果显示框
        jTextArea = new JTextArea();
        jTextArea.setBounds(0,75,600,225);
        jScrollPane = new JScrollPane(jTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setBounds(0,75,580,180);
        jPanel.add(jScrollPane);

        // 显示窗口
        window.add(jPanel);
        window.setVisible(true);
    }

    /**
     * 连接到服务器
     */
    private void connect() {
        try {
            socket = new Socket(ADDRESS, PORT);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            connected = true;
            button_connect.setText("断开连接");
            jPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 断开连接
     */
    private void release() {
        try {
            outputStream.writeUTF("!end");
            outputStream.flush();

            socket.close();
            inputStream.close();
            outputStream.close();

            connected = false;
            button_connect.setText("连接服务器");
            jPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户
     */
    private void getResult(@NotNull String User_name) {
        if (!connected || User_name.equals("")) return;
        try {
            outputStream.writeUTF(User_name);
            String s = inputStream.readUTF();
            System.out.println(s);
            jTextArea.append(s);
            jTextArea.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        systemClient client = new systemClient();
    }
}
