package NeuralNetwork;
public class BP_Close_FUN {
    public static final Double study_speed=0.1;         //学习速度
    public static final int layer_num=10;               //预设10层神经网络
    public static final int net_num=3;                    //每层神经元个数
    public static final String fun="3x3+-2x2+6x1+2x0";         //目标函数，我们需要逼近的函数,x之前的代表系数，x之后的代表阶数
    
    public void  getFun(Double x){
    	String[] a=fun.split("\\+");
    	Double sum=0d;
    	for(int i=0;i<a.length;i++){
    		System.out.println(a[i]);
    		Double sum_this=1d;
    		String[] snow=a[i].split("x");
    		Double pre=Double.parseDouble(snow[0]);           //snow[0]储存的是系数
    		int  jie=Integer.parseInt(snow[1]);              //snow[1]储存的是阶数
    	     for(int m=0;m<jie;m++){
    	    	 sum_this=sum_this*x;
    	     }
    	     sum_this=sum_this*pre;                                //得到其中一项，如aX的3次方的值
    	     sum=sum+sum_this;
    	}	
    	System.out.println("总和是："+sum);
    }
    public static void main(String[] args){
    	BP_Close_FUN bp=new BP_Close_FUN();
    	bp.getFun(2d);
    }
}
