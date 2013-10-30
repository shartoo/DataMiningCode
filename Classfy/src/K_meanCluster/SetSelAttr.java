package K_meanCluster;
import java.awt.Choice;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import Genera_decis_tree.PublicData;
public class SetSelAttr extends MouseAdapter implements ActionListener{
     JList allList=new JList();
     JList selList=new JList();
     DefaultListModel modeAll=null;
     DefaultListModel modeSel=null;
     JLabel allAttr=new JLabel("所有属性");
     JLabel selAttr=new JLabel("聚类属性");
     JLabel tip=new JLabel("请指定聚类个数K");
     JButton setAttr=new JButton("设置聚类属性");
     JButton submit=new JButton("提交");
     final Choice clusterNum = new Choice();    //用户选择聚类个数
     
     JScrollPane allPane=new JScrollPane();
     JScrollPane selPane=new JScrollPane();
     Boolean right=false;   //属性右移开关
     Boolean left=false;    //属性左移开关
     public int index=0;    //设置在JList中属性的索引
     public int cluNum=0;
     final JFrame f=new JFrame("设置聚类的属性");
     public String[] all=PublicData.getAttr();   //获取数据表中的所有属性
     
     public void CreateUI()
     {   
         setAttr.addActionListener(this);
         submit.addActionListener(this);
         clusterNum.addItemListener(new ItemListener()
         {
        	 public void itemStateChanged(ItemEvent arg0) 
        	   {  		 
        		  cluNum=clusterNum.getSelectedIndex()+2;
        		  System.out.println("产生的个数是"+cluNum+"--------来自itemStateChanged");
        	      submit.addMouseListener(new MouseListener(){
        	      public void mouseClicked(MouseEvent arg0) 
        	      {
        	             // TODO 自动生成方法存根
        	      }
        	      public void mouseEntered(MouseEvent arg0) 
        	      {
        	         // TODO 自动生成方法存根
        	      }
        	      public void mouseExited(MouseEvent arg0) 
        	      {
        	         // TODO 自动生成方法存根
        	      }
        	     public void mousePressed(MouseEvent arg0) 
        	      {
        	        // TODO 自动生成方法存根
        	      }
        	     public void mouseReleased(MouseEvent arg0) 
        	     {
        		   cluNum=clusterNum.getSelectedIndex()+2;}
        	    });
        	 }
        	 });     
         //添加2--10的聚类个数
         for(int i=0;i<9;i++)
         {
        	 String s=i+2+"";
        	 clusterNum.addItem(s);
         }              
         Image icon = Toolkit.getDefaultToolkit().getImage("D:\\predata\\120.jpg");
     	 f.setIconImage(icon);
     	//Container contentPane=f.getContentPane();
    	//contentPane.setLayout(new GridLayout(3,3));
    	f.setLayout(new GridLayout(3,3));
     	modeAll=new DataModel(1);
    	allList=new JList(modeAll);
    	allList.setBorder(BorderFactory.createTitledBorder("当前所有属性"));
    	allList.addMouseListener(this);//一遇到鼠标事件立即执行.

    	modeSel=new DataModel(2);
    	selList=new JList(modeSel);
    	selList.setBorder(BorderFactory.createTitledBorder("作为自变量的属性"));
    	selList.addMouseListener(this);//一遇到鼠标事件立即执行.
    	 
    	allPane.setViewportView(allList);
    	selPane.setViewportView(selList);
    	f.add(allAttr);
    	f.add(new JLabel(""));   //插入空白标签以形成3*3个组件
    	f.add(selAttr);
    	
    	f.add(allPane);
    	f.add(setAttr);
    	f.add(selPane);
    	
    	f.add(tip);
    	f.add(clusterNum);
    	f.add(submit);
    	
    	f.setBounds(400,100,300,400);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter(){
          public void windowClosing(WindowEvent e){
             f.dispose();
          }
       });  
    	
    	
     }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s=e.getActionCommand();
		//控制JList项右移
		if(s.equals("设置聚类属性")&&right==true)
		{  
			String tmp=(String) modeAll.getElementAt(index);
			 modeSel .addElement(tmp);
		     selList.setModel(modeSel);
		     modeAll.removeElementAt(index);
		     allList.setModel(modeAll);
			
		}
		//控制JList项左移
		else if(s.equals("设置聚类属性")&&left==true)
		{  
			String tmp=(String) modeSel.getElementAt(index);
			 modeAll .addElement(tmp);
		     allList.setModel(modeAll);
		     modeSel.removeElementAt(index);
		     selList.setModel(modeSel);
			
		}	
		else if(s.equals("提交")){
			System.out.println("用户选择的聚类个数是"+this.getclusterNum());
		    String clusterAttr=null;
			f.dispose();
			f.invalidate();
			f.repaint();
			for(int i=0;i<selList.getModel().getSize();i++){
				if(i==0)
					clusterAttr=(String)selList.getModel().getElementAt(i);
				else
					clusterAttr=clusterAttr+","+(String)selList.getModel().getElementAt(i);
			}
			  PublicData.setClusterAttr(clusterAttr);     //将用户选择的用于聚类的属性更新到数据中心
			  String num=this.getclusterNum()+"";
			  PublicData.setClusterNum(num);
			  Cluster clu=new Cluster();
			  clu.doCluster();
		}
		
	}
	class DataModel extends DefaultListModel{
		   DataModel(int flag){
		      if (flag==1){
		      for (int i=0;i<all.length;i++) addElement(all[i]);         
		      }
		   }
		}  
	//处理鼠标键击事件.
	public void mouseClicked(MouseEvent e){
	    /*对list1而言，当鼠标在某个项目连续按两下时，我们利用JList所提供的locationToIndex()方法，找到所键击的项目，并
	     *由tmp取得此项目的项目值，然后将此项目值增加到mode2中[mode2.addElement(tmp)],用setModel重新设置list2的
	     *ListModel,使list2可显示出所增加的项目，将刚刚在list1双击的项目删除.
	     */
		if (e.getSource()==allList){
	    	right=true;
	    	left=false;
	       index=allList.locationToIndex(e.getPoint());
	      }
		if (e.getSource()==selList){
	    	left=true;
	    	right=false;   //是一对开关  	
	       index=selList.locationToIndex(e.getPoint());
	      }
	 }
	public int getclusterNum(){
		return cluNum;
	}
	/**
	public static void main(String[] args){
		SetSelAttr ssa=new SetSelAttr();
		ssa.CreateUI();
		System.out.println("用户选择的聚类个数是(主函数调用---)"+ssa.getclusterNum());
	}
   */
}
