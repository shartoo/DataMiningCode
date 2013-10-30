package Genera_decis_tree;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
/**
 * 我们来看如何处理在JList上双击鼠标的操作。由于JList本身并无提供这样的EventListener,因此我们必须利用
MouseListener来达到捕获双击鼠标的事件。至于要怎么知道我们到底在哪个项目上双击鼠标呢？我们可以利用JList类所提供的
LocatToindex()方法得知。以下为我们所举的范例:
   这个例子一开始左边列有国这名称，当你在某个国家名称上双击鼠标，这个国家名称就会移到右边去，反之亦同。
   1.这个范例我们应用DefaultListModel类，因主DefaultListModel类实作了Vector中的方法，使我们在处理动态的JList项目值 
     比较容易.
   2.由于我们要处理鼠标事件，为了编写方便，在程序中我们继承MouseAdapte抽象类.
   3.在程序中，我们建立两个DataModel，第一个JList,也就是list1一开始时会将String Array s中的所有值依次放入list1的项
     目中，而list2一开始为空白。
 *
 */
public class Desion_Tree_SetAttr extends MouseAdapter implements ActionListener{
	/**
	 * 决策树中用来设置分类属性的frame
	 */
JList allattrlist=null; 
JList classattrlist=null; 
JList tarlist=null;
DefaultListModel mode1=null;
DefaultListModel mode2=null;
DefaultListModel mode3=null;

JLabel var=new JLabel("变量");
//JLabel selvar=new JLabel("自变量");
JLabel tarvar=new JLabel("因变量");
JPanel right=new JPanel();
JButton tarBut=new JButton("设置因变量");
JButton selBut=new JButton("设置自变量");
JButton submit=new JButton("提交");
JLabel blank=new JLabel();     //空白标签，用来布局时将提交按钮放在最右边

JScrollPane varpane=new JScrollPane();
JScrollPane tarpane=new JScrollPane();
JScrollPane selpane=new JScrollPane();

PublicData pd=new PublicData();
/**
 * 控制是否可以左右移动
 */
public static boolean leftFlag=false;
public static boolean rightFlag=false;

public int index=0;                    //整个类公用的 存储当前选择的list项的索引

String[] s = PublicData.getAttr();
public static JFrame f=new JFrame("决策树属性设置");                 //不放在外面，会导致点击窗口退出时全部退出
EveryStepGain  evegain=new EveryStepGain();
public void creatUI(){
	s = PublicData.getAttr();
	Image icon = Toolkit.getDefaultToolkit().getImage("D:\\predata\\120.jpg");
	f.setIconImage(icon);
    f.setLayout(new GridLayout(3,2));
	submit.setLocation(650,350);
	submit.setSize(20, 10);
	submit.addActionListener(this);
    //var.setLocation(100, 100);
	
	tarBut.addActionListener(this);
	selBut.addActionListener(this);

	mode1=new DataModel(1);
	allattrlist=new JList(mode1);
	allattrlist.setBorder(BorderFactory.createTitledBorder("当前所有属性"));
	allattrlist.addMouseListener(this);//一遇到鼠标事件立即执行.

	mode2=new DataModel(2);
	classattrlist=new JList(mode2);
	classattrlist.setBorder(BorderFactory.createTitledBorder("作为自变量的属性"));
	classattrlist.addMouseListener(this);//一遇到鼠标事件立即执行.

	mode3=new DataModel(3);
	tarlist=new JList(mode3);
	tarlist.setVisibleRowCount(1);           //设置其显示行数，只显示一行
	tarlist.addMouseListener(this);
	tarlist.setBorder(BorderFactory.createTitledBorder("作为因变量的属性"));
	
	var.setBounds(420,120,20,15);
	//var.setSize(20,15);
    varpane.setBounds(420,140,60,300);
    //varpane.setSize(60,300);
    
    tarBut.setBounds(500,55,30,20);
    //tarBut.setSize(30,20);
    selBut.setBounds(500,175,30,20);
    //selBut.setSize(30,20);
    
    tarvar.setBounds(550,45,20,10);
    //tarvar.setSize(20,10);
    tarpane.setBounds(550,140,60,10);
    //tarpane.setSize(60,10);
    
   // selvar.setBounds(550,185,30,15);
    //selvar.setSize(30,15);
    
    selpane.setBounds(550,215,60,200);
    //selpane.setSize(60,200);
    
    varpane.setViewportView(allattrlist);
   tarpane.setViewportView(tarlist);
    selpane.setViewportView(classattrlist);
    
	right.setLayout(new GridLayout(2,2));
	right.add(tarBut);
	//right.add(tarlist);
	right.add(tarpane);
	right.add(selBut);
	//right.add(new JScrollPane(list2));
	right.add(selpane);
   
	//f.removeAll();
	f.add(var);
	f.add(tarvar);
   // contentPane.add(new JScrollPane(list1));
	f.add(varpane);
	
	f.add(right);
	f.add(blank);
	f.add(submit);
 
	f.setBounds(400,100,300,400);
    f.setVisible(true);
    f.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
         f.dispose();
      }
   });    
}
class DataModel extends DefaultListModel{
   DataModel(int flag){
      if (flag==1){
      for (int i=0;i<s.length;i++) addElement(s[i]);         
      }
   }
}
@Override
public void actionPerformed(ActionEvent e) {
	String s=e.getActionCommand();
	if(s.equals("设置因变量")&&(tarlist.getModel().getSize()==0)&&rightFlag==true){            //向目标变量List中添加结点
		String tmp=(String)mode1.getElementAt(index);
	    System.out.println("添加因变量"); 
		 mode3 .addElement(tmp);
	     tarlist.setModel(mode3);
	     mode1.removeElementAt(index);
	     allattrlist.setModel(mode1);
	}
	else if(s.equals("设置因变量")&&tarlist.getModel().getSize()>0&&leftFlag==true){   //从目标变量List中移除结点
		String tmp=(String)mode3.getElementAt(index);
		System.out.println("移除因变量");
		mode1.addElement(tmp);
		allattrlist.setModel(mode1);
		mode3.removeElementAt(index);
		tarlist.setModel(mode3);
	}
	else if(s.equals("设置自变量")&&rightFlag==true){
		String tmp=(String)mode1.getElementAt(index);
		mode2.addElement(tmp);
		classattrlist.setModel(mode2);
		mode1.removeElementAt(index);
		allattrlist.setModel(mode1);
	}
	else if(s.equals("设置自变量")&&leftFlag==true){
		String tmp=(String)mode2.getElementAt(index);
		mode1.addElement(tmp);
		allattrlist.setModel(mode1);
		mode2.removeElementAt(index);
		classattrlist.setModel(mode2);	
	}
	else if(s.equals("提交"))
	{
		String tmp=null;
		//allattrlist.clearSelection();
		//classattrlist.clearSelection();
		//tarlist.clearSelection();	
		if(tarlist.getModel().getSize()==1){
			tmp=(String)tarlist.getModel().getElementAt(0);
		    System.out.println("there is data"+"===="+tmp);
		    pd.setTarvar(tmp);
		}
		String v=(String)classattrlist.getModel().getElementAt(0);
		for(int i=1;i<classattrlist.getModel().getSize();i++){
			v=v+","+(String) classattrlist.getModel().getElementAt(i);
		}
		String[] var=v.split(",");
		pd.setSelvar(var);
		allattrlist.clearSelection();
		classattrlist.clearSelection();
		tarlist.clearSelection();
		
		evegain.showDescionTree();
		f.dispose();
		f.invalidate();
		f.repaint();
	}
}

//处理鼠标键击事件.如果放在actionPerformed前面会导致
public void mouseClicked(MouseEvent e){
    /*对list1而言，当鼠标在某个项目连续按两下时，我们利用JList所提供的locationToIndex()方法，找到所键击的项目，并
     *由tmp取得此项目的项目值，然后将此项目值增加到mode2中[mode2.addElement(tmp)],用setModel重新设置list2的
     *ListModel,使list2可显示出所增加的项目，将刚刚在list1双击的项目删除.
     */
	if (e.getSource()==allattrlist){
    	rightFlag=true;
    	leftFlag=false;  	
       index=allattrlist.locationToIndex(e.getPoint());
      }
	if (e.getSource()==classattrlist){
    	leftFlag=true;
    	rightFlag=false;   //是一对开关  	
       index=classattrlist.locationToIndex(e.getPoint());
      }
	if(e.getSource()==tarlist){
	   leftFlag=true;
	   rightFlag=false;
	   //index=tarlist.locationToIndex(e.getPoint());
	   index=0;
      }
 }

public static void main(String[] args){
    new Desion_Tree_SetAttr().creatUI();
}

}