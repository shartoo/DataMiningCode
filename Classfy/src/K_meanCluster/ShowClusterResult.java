package K_meanCluster;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Genera_decis_tree.PublicData;

public class ShowClusterResult {
	 public static final String clusterResult="Data/Cluster/clusterResult.txt";
	 public static final String clusterInitCore="Data/Cluster/clusterInitCore.txt";
	 public static final Color color[]={Color.cyan,Color.lightGray,Color.blue,Color.green,Color.magenta,Color.orange,Color.pink};
   	 public int clusternum=PublicData.getClusterNum();
	 public String[] attr=PublicData.getClusterAttr();
     JFrame f=new JFrame("快速聚类结果显示");
     DefaultTableCellRenderer tcr;
     Image icon = Toolkit.getDefaultToolkit().getImage("img/logo.jpg");
     public void show()
     {
    	 f.setIconImage(icon);
         f.setLocation(100,200);
         JScrollPane pane=new JScrollPane();
         pane.setViewportView(this.getTable());
    	 f.getContentPane().add(pane);
         f.setVisible(true);
         f.setSize(f.getPreferredSize());
    	 
     }
     public JTable getTable(){
    	 JTable table;
    	 ArrayList<ArrayList<String>> realList=this.getRealCore();
    	 ArrayList<ArrayList<String>> initList=this.getInitCore();
         int[] color=new int[attr.length];
         for(int i=0;i<color.length;i++){
        	 color[i]=i+5;
         }
         
         
    	 for(int i=0;i<realList.size();i++){
    		 for(int j=0;j<realList.get(0).size();j++){
    			 System.out.print("realList的值有"+realList.get(i).get(j)+"\t");
    		 }
    		 System.out.println();
    	 }
    	 
    	 
    	 
    	 int attrnum=attr.length; 
    	 table=new JTable(realList.size()+2,realList.get(1).size());    //加2，是第一行作为辨别行，第二行显示初始的随机聚类中心
    	 
    	 table.setValueAt("聚类次数",0,0);       //设置JTbale的最左上角显示内容
    	for(int i=0;i<attrnum;i++){
    		for(int j=0;j<clusternum;j++){
    			if(i==0)
    			  {   table.setValueAt(attr[i],0,j+1);
    			       System.out.println("此处的值是："+attr[i]);
    			  
    			  }
    			
    			else
    			  {
    				table.setValueAt(attr[i],0,i*clusternum+j+1);
    				 System.out.println("此处的值是(非第一列)："+attr[i]);
    			  }	
    		}
    	}
    	//接下来写入内容
    	//首先写入初始聚类中心
    	table.setValueAt("初始聚类中心",1,0);
       	for(int i=0;i<attrnum;i++){
    		for(int j=0;j<clusternum;j++){
    			if(i==0)
    			  {   table.setValueAt(initList.get(i).get(j+1),1,j+1);
    			       System.out.println("此处的值是："+attr[i]);
    			  
    			  }
    			
    			else
    			  {
    				table.setValueAt(initList.get(i).get(j+1),1,i*clusternum+j+1);
    				 System.out.println("此处的值是(非第一列)："+attr[i]);
    			  }	
    		}
    	}
    	//再读入成功聚类中心结果
       	System.out.println("我们创建的table的行数是"+table.getRowCount()+"列长度是"+table.getColumnCount());
       	this.setCellRenerer();    
       	for(int i=0;i<realList.size();i++){
       		table.setValueAt(i, i+2, 0);
       		for(int j=0;j<realList.get(i).size()-1;j++){
			      			
       			table.setValueAt(realList.get(i).get(j), i+2, j+1);
       		   table.getColumn(table.getColumnName(j)).setCellRenderer(tcr);
            
       			
       		  // table.setBackground(new Color(color[i%attr.length])); 
       		}  		
       	}
    	 return table;
     }
     
     
    
     //读入初始聚类中心，以ArrayList返回，注意其结构，大list中的小list的第一个位置存储的是属性在所用属性列表中的位置而且是BigDecimal类型
     public ArrayList<ArrayList<String>> getInitCore(){
    	 ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>();
    	 String f="";
    	 try{
    		 BufferedReader read=new BufferedReader(new FileReader(clusterInitCore)); 		 
    		 while((f=read.readLine())!=null)
    		 {
    		  ArrayList<String> arr=new ArrayList<String>();
    		  String[] s=f.split("\\\t");
    		  System.out.println("载入初始聚类中心-----------");
    		  for(int i=0;i<s.length;i++)
    		    {
    			   if(s[i]!=null)
    			         {
    			    	  arr.add(s[i]);
    			    	  System.out.print(s[i]+"\t");
    			         }
    		   }
	         list.add(arr);
    		 } 
    		 System.out.println("initCore的list加入了几个"+list.size());
    	 }
    	 catch(Exception e){
    		 e.printStackTrace();
    	 } 
    	 return list;
     }
  //返回聚类结果，与初始聚类类似，与上面的初始聚类存储结构不同。聚类结果可以看其文本文件。
     public ArrayList<ArrayList<String>> getRealCore(){
    	 ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>();
    	 int attrnum=PublicData.getClusterNum();
    	 int selnum=PublicData.getClusterAttr().length;
    	 String f="";
    	 int j=0;              //起初的两行是自己加入的，没有意义。不可以读入
    	 try{
    		 BufferedReader read=new BufferedReader(new FileReader(clusterResult));
    		 while((f=read.readLine())!=null)
    		 {
    			 j++;   			 
    			 if(j>2)
    			 {
    		       ArrayList<String> arr=new ArrayList<String>();
    		//空格,制表符，等进行拆分（也就是说它是按空白部分进行拆分，不管这个空白使用什么操作留下的,比如空格键 tab键，或者回车键）。       
    		       String[] s=f.split("\\s+",PublicData.getClusterNum()*attr.length+2);
    		      // String[] s=f.split("/s",PublicData.getClusterNum()*attr.length+2);
    		       System.out.println("我们选择的拆分长度是"+PublicData.getClusterNum()*attr.length+2+"\t"+f);
    		       for(int i=0;i<s.length;i++)
    		       {
    			      if(s[i]!=null&&s[i]!="")
    			         {
    			    	  arr.add(s[i]);
    			         }
    		       }
    		       if(!(arr.size()<attrnum*selnum+1))        //总是莫名的加入混乱的数据，不得不去掉一部分数据
	                    list.add(arr);
    			}
    		 } 
    		 read.close();
    	 }
    	 catch(IOException e){
             e.printStackTrace();	
    	 }
    	 return list;
     }
     //用于设置某列或者某行底色的方法
     public void setCellRenerer(){
    	 int num=0;
    	 tcr = new DefaultTableCellRenderer() {
    	        public Component getTableCellRendererComponent(JTable table,
    	            Object value, boolean isSelected, boolean hasFocus,
    	            int row, int column) {
    	        	int m=column-1;
    	        	if(column==0)
    	        		setBackground(Color.white);
    	        	else
    	               setBackground(color[m/clusternum]); //设置偶数行底色
    	          return super.getTableCellRendererComponent(table, value,
    	              isSelected, hasFocus, row, column);
    	        }
    	      }; 
     }

   public static void main(String[] args){
	   ShowClusterResult result=new ShowClusterResult();
	   result.show();
   }
}

