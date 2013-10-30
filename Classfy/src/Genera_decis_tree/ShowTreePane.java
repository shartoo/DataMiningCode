package Genera_decis_tree;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ShowTreePane extends JPanel{
     public static ArrayList<TreeNode> list=new ArrayList<TreeNode>(); // list用来存储node类型的结点，画Tree时直接从list中取值
     public float height=600;
     public float width=1200;
     public float  nodeW;    //每次树结点的宽度
     public float  R;     //椭圆结点半径
     public int nodeX=300;   //决策树中头结点的x和y坐标
     public int nodeY=50;
     public static final int Node_width=70;
     public static final int Node_height=50;
     //public static final int lag=
     public static String Head_Node_Name;
     InformationGain ing=new InformationGain();
     public HashMap<String,String[]>  attrmap=ing.getMap();
     public HashMap<String,ArrayList<String>> attrAndPart=new HashMap<String,ArrayList<String>>(); 
 	 ArrayList<String> lt=new ArrayList<String>(); 	 
 	HashMap<String,String[]> map=new HashMap<String,String[]>();    //通过list来取结点，从map中判断其分支
    public void addNode(String name,int yes,int no,float gain,int row,int Column){
    	 TreeNode tn=new TreeNode(name,yes,no,gain,row,Column);
    	// lt.add(name);
    	 list.add(tn);
     }
     public void setMap(HashMap<String,String[]> map){
    	 this.map=map;
    	 if(map.size()!=0)
    		 System.out.println("从informationGain中更新过来的所有的属性及其分支成功！");
     }
     public JPanel getTreePane(){
    	 JPanel jp=new ShowTreePane();    	 
    	 return jp;
     }
     public void paint(Graphics g){                         //画结点
    	 super.paint(g);
    	 g.setColor(Color.black);
    	 ArrayList<ArrayList<TreeNode>> sortNode=new ArrayList<ArrayList<TreeNode>>();
    	 int layer=0;             //存储树有多少层节点
    	 int headIndex=0;
    	 //先获取头结点和总得层数 
    	 for(int i=0;i<list.size();i++){
    		 if(list.get(i).getRow()==0){
    			 Head_Node_Name=list.get(i).getName();
    			 headIndex=i;
    		 }
    		 if(list.get(i).getRow()>layer)
    			 layer=list.get(i).getRow()+1;          //小心，row从第0行开始的   		 
    	 }
    	 //先将我们的属性及其分支的hash表从key=String   value=String[]转换成key=String   value=ArrayList<String>方便后面contains使用
    	   	Set<Map.Entry<String,String[]>> checkset = attrmap.entrySet();
        	Iterator<Map.Entry<String, String[]>> checkit=checkset.iterator();
        	while(checkit.hasNext())
        	    {
        		 Map.Entry<String,String[]> checkentry =(Map.Entry<String, String[]>) checkit.next();
     	         String[] allpart=checkentry.getValue();
     	         String attrname=checkentry.getKey();
    	        ArrayList<String> partlist=new ArrayList<String>();
    	        for(int i=0;i<allpart.length;i++)
    	         {
    	        	partlist.add(allpart[i]);
    	         }
    	        attrAndPart.put(attrname,partlist);
        	 }
        	
    	//再将所有的结点整理，每一行每一列
        int nullIndex=0;	
    	 for(int i=0;i<layer;i++)
    	 {
    		ArrayList<TreeNode> tree=new ArrayList<TreeNode>(); 
    	   for(int j=0;j<list.size();j++)
    	   {
    		   if(list.get(j).getRow()==i)    //同属于第i行的加在一起
    			   tree.add(list.get(j));      //此时的加入结点是无序的，有可能第3行第2列的被加到第一个位置，而第5个被加入到地2个位置
    		   
    	   }
    	  if(tree.size()==0)
    		  nullIndex=i;
    	      sortNode.add(tree); 
    	 }
        sortNode.remove(nullIndex);

    	 //绘制头结点
    	 g.setColor(Color.green);
   	     g.fillOval(nodeX,nodeY,70,50);
   	     g.setColor(Color.black);
   	     g.drawString(Head_Node_Name,nodeX+17, nodeY+23);  	  
   	     g.drawString("信息增益    "+list.get(headIndex).getGain(),nodeX+80, nodeY-15); 	  
   	     g.drawString("支持度       "+list.get(headIndex).getYES(),nodeX+80, nodeY);    //画出支持度和否决度及信息增益 	 
   	     g.drawString("否决度      "+list.get(headIndex).getNO(),nodeX+80, nodeY+12);
    	 //第0层是头结点，上面已经画过
         System.out.println("一共有多少层树"+layer);
         int lag=0;
    	 for(int i=1;i<layer-1;i++)
    	 {
    		 System.out.println("第"+i+"层树开始其中"+sortNode.get(i)+"有几个结点"+sortNode.get(i).size());  
    		 for(int j=0;j<sortNode.get(i).size();j++)
    		 {   			      
    			     lag=(int)width/(sortNode.get(i).size()+1);   //计算出每一行各个节点的间距
    			     Font font0=g.getFont();
    		   	     Font font1=new Font("TimesRoman",Font.ITALIC,15);
    			     g.setColor(Color.green);
    		   	     g.fillOval(j*lag,nodeY+i*100,Node_width,Node_height);  //绘制结点
    		   	     g.setColor(Color.black);
    		   	     String part=sortNode.get(i).get(j).getName().split(",")[0];
    		   	     String attr=sortNode.get(i).get(j).getName().split(",")[1];
    		   	     g.setFont(font1);
    		   	     g.setColor(Color.BLUE);
    		   	     g.drawString(part,j*lag+10, nodeY+i*100-30);
    		   	     g.setFont(font0);
    		   	     g.setColor(Color.BLACK);
    		   	     g.drawString(attr,j*lag+15, nodeY+i*100+25);
    		   	     System.out.println("外层的i有多少i=="+i);
    		   	     System.out.println("sortNode的节点个数是："+sortNode.get(i-1).size());
    		  	      for(int k=0;k<sortNode.get(i-1).size();k++)
    		   	        {     //如果上一层树中的某个结点包含本层结点的分支，画一条直线连接
    		  	    	   String[] nodeName=sortNode.get(i-1).get(k).getName().split(",");
    		  	    	   
    		   	    	    System.out.println("key是"+sortNode.get(i-1).get(k).getName()+"即将查看的是"+attrAndPart.get(sortNode.get(i-1).get(k).getName()));
    		   	    	    if(attrAndPart.get(nodeName[0])!=null)
    		   	    	    {
    		   	              if(attrAndPart.get(sortNode.get(i-1).get(k).getName()).contains(part))
    		   	        	     g.drawLine(j*lag+15+Node_height/2,nodeY+i*100,(k+1)*lag+Node_width/2,nodeY+(i-1)*100+Node_height);       
    		   	    	  } 
    		   	    	  else 
    		   	    	  {             //此时必然是nodeName包含了两个string，例如：middle-aged,credit,而上面只有一个，如;age  	
    		   	    		 if(attrAndPart.get(nodeName[1]).contains(part))
		   	        	        g.drawLine(j*lag+15+Node_height/2,nodeY+i*100,k*lag+Node_width/2,nodeY+(i-1)*100+Node_height); 
    		   	    	 }
    		   	       }    		   	      
    		   	     g.drawString("信息增益: "+sortNode.get(i).get(j).getGain(),j*lag+70, nodeY+i*100-30);
    		   	     g.drawString("支持度:   "+sortNode.get(i).get(j).getYES(),j*lag+70, nodeY+i*100-17);    //画出支持度和否决度及信息增益
    		   	     g.drawString("否决度:   "+sortNode.get(i).get(j).getNO(),j*lag+70, nodeY+i*100); 	     
    		   	    // g.drawLine(j*lag,nodeY+(i-1)*100,j*lag+Node_height/2,nodeY+i*100);  //画直线，都是两椭圆的与x轴平行的切点处的两个点
    		 }		 
    	 }  	 
     }
}
