package Experiment1;

public class Area{
    public static void main (String[] arg){
        MyCircle c=new MyCircle(1,1,3);
        MySquare s=new MySquare(2,2,4);
        MyRectangle r=new MyRectangle(12,9,1,2);
        MyEllipse e=new MyEllipse(2,-1,3,2);
        System.out.println("圆c的面积是"+c.area());
        System.out.println("正方形s的面积是"+s.area());
        System.out.println("矩形r的面积是"+r.area());
        System.out.println("椭圆e的面积是"+e.area());
    }
}
