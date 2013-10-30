package K_meanCluster;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ShowClusterGraph extends JFrame{
   ShowClusterResult result=new ShowClusterResult();
   ArrayList<ArrayList<String>> real=result.getRealCore();
   ArrayList<ArrayList<String>> init=result.getInitCore();
   public static final Color color[]={Color.cyan,Color.lightGray,Color.blue,Color.green,Color.magenta,Color.orange,Color.pink};
   
   public void show(){
	   this.setTitle("聚类过程图显示");
	   for(int i=0;i<real.size();i++)
	   {
		   for(int j=0;j<real.get(i).size();j++){
		        System.out.print(real.get(i).get(j)+"\t");	   
		   }
		   System.out.println("来自real的值");
	   }	   
	   
   }
   public void paint(Graphics g){   
	   super.paint(g);
	   
   }
   
   
   
   
   public static void main(String[] args){
	   ShowClusterGraph grap=new ShowClusterGraph();
	   grap.show();
   }
   
}
