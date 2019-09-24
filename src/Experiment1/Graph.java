package Experiment1;

abstract class Graph{
    protected double x,y;				// x,y是规则图形的中心点坐标
    public Graph(double x,double y){		// 构造函数初始化中心点坐标
        this.x=x;
        this.y=y;
    }
    protected void changeX(double x){		// 修改横坐标
        this.x=x;
    }
    protected void changeY(double y){		// 修改纵坐标
        this.y=y;
    }
    public abstract double area();			// 计算面积的抽象方法
}

class MySquare extends Graph{
    private double length;
    public MySquare(double x,double y,double length){
        super(x,y);
        this.length=length;
    }
    protected void changLength(double length){   // 修改边长length
        this.length=length;
    }
    public double area(){
        return length*length;
    }
}

class MyCircle extends Graph{
    private double radius;
    public MyCircle(double x,double y,double radius){
        super(x,y);
        this.radius=radius;
    }
    protected void changRadius(double radius){   // 修改半径radius
        this.radius=radius;
    }
    public double area(){
        return 3.1416*radius*radius;
    }
}

class MyRectangle extends Graph{
    private double a,b;
    public MyRectangle(double x,double y,double a,double b){
        super(x,y);
        this.a=a;
        this.b=b;
    }
    protected void changLength(double length){   // 修改长length
        a=length;
    }
    protected void changWidth(double width){   // 修改宽width
        b=width;
    }
    public double area(){
        return a*b;
    }
}

class MyEllipse extends Graph{
    private double a,b;
    public MyEllipse (double x,double y,double a,double b){
        super(x,y);
        this.a=a;
        this.b=b;
    }
    protected void changA(double a){   // 修改长轴a
        this.a=a;
    }
    protected void changB(double b){   // 修改短轴b
        this.b=b;
    }
    public double area(){
        return 3.1416*a*b;
    }
}
