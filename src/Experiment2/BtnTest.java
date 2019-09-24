package Experiment2;

/**
 * @Author Fisher
 * @Date 2019/9/10 10:07
 **/

import java.awt.*;
import java.awt.event.*;   //引入java.awt.event包处理事件
class BtnLabelAction extends Frame implements ActionListener{
    //声明窗口类（BtnLabelAction）并实现动作事件接口（ActionListener）
    Label prompt;
    Button btn;
    Button ext;
    void CreateWindow(){  //自定义方法
        setTitle("MyButton");
        prompt = new Label("Hello");	//创建标签对象
        btn = new Button("Operate");		//创建按钮对象
        ext=new Button(("exit"));
        setLayout(new FlowLayout());	//布局设计，用于安排按钮、标签的位置
        add(prompt);				//将标签放入容器
        add(btn);					//将按钮放入容器
        add(ext);
        btn.addActionListener(this);	//将监听器（窗体对象本身）注册给按钮对象
        ext.addActionListener(this);
        setSize(300,100);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){//接口ActionListener的事件处理方法
        if(e.getSource()==btn)  //判断动作事件是否是由按钮btn引发的
            if(prompt.getText().equals("Hello"))
                prompt.setText("Bye");
            else
                prompt.setText("Hello");
        else
        if(e.getSource()==ext)
            System.exit(0);
    }
}

public class BtnTest{
    public static void main (String args[]){
        BtnLabelAction bla=new BtnLabelAction();
        bla.CreateWindow();
    }
}
