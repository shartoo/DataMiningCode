package Genera_decis_tree;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class Desion_Tree_SetAttr extends MouseAdapter implements ActionListener{
JList allattrlist=null; 
JList classattrlist=null; 
JList tarlist=null;
DefaultListModel mode1=null;
DefaultListModel mode2=null;
DefaultListModel mode3=null;

JLabel var=new JLabel("全部属性");
//JLabel selvar=new JLabel("�Ա���");
JLabel tarvar=new JLabel("目标属性");
JPanel right=new JPanel();
JButton tarBut=new JButton("移动决策属性");
JButton selBut=new JButton("移动一般属性");
JButton submit=new JButton("提交");
JLabel blank=new JLabel();     

JScrollPane varpane=new JScrollPane();
JScrollPane tarpane=new JScrollPane();
JScrollPane selpane=new JScrollPane();

PublicData pd=new PublicData();
/**
 * �����Ƿ���������ƶ�
 */
public static boolean leftFlag=false;
public static boolean rightFlag=false;

public int index=0;                    //����๫�õ� �洢��ǰѡ���list�������

String[] s = PublicData.getAttr();
public static JFrame f=new JFrame("决策树设置分类属性及普通属性");                 //���������棬�ᵼ�µ�������˳�ʱȫ���˳�
EveryStepGain  evegain=new EveryStepGain();
public void creatUI(){
	s = PublicData.getAttr();
	Image icon = Toolkit.getDefaultToolkit().getImage("img/120.jpg");
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
	allattrlist.setBorder(BorderFactory.createTitledBorder("全部属性"));
	allattrlist.addMouseListener(this);//һ��������¼�����ִ��.

	mode2=new DataModel(2);
	classattrlist=new JList(mode2);
	classattrlist.setBorder(BorderFactory.createTitledBorder("一般属性"));
	classattrlist.addMouseListener(this);//һ��������¼�����ִ��.

	mode3=new DataModel(3);
	tarlist=new JList(mode3);
	tarlist.setVisibleRowCount(1);           //��������ʾ����ֻ��ʾһ��
	tarlist.addMouseListener(this);
	tarlist.setBorder(BorderFactory.createTitledBorder("决策属性"));
	
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
 
	f.setBounds(400,100,500,500);
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
	if(s.equals("移动决策属性")&&(tarlist.getModel().getSize()==0)&&rightFlag==true){            //��Ŀ�����List����ӽ��
		String tmp=(String)mode1.getElementAt(index);
	    System.out.println("移动决策属性"); 
		 mode3 .addElement(tmp);
	     tarlist.setModel(mode3);
	     mode1.removeElementAt(index);
	     allattrlist.setModel(mode1);
	}
	else if(s.equals("移动决策属性")&&tarlist.getModel().getSize()>0&&leftFlag==true){   //��Ŀ�����List���Ƴ���
		String tmp=(String)mode3.getElementAt(index);
		System.out.println("移动决策属性");
		mode1.addElement(tmp);
		allattrlist.setModel(mode1);
		mode3.removeElementAt(index);
		tarlist.setModel(mode3);
	}
	else if(s.equals("移动一般属性")&&rightFlag==true){
		String tmp=(String)mode1.getElementAt(index);
		mode2.addElement(tmp);
		classattrlist.setModel(mode2);
		mode1.removeElementAt(index);
		allattrlist.setModel(mode1);
	}
	else if(s.equals("移动一般属性")&&leftFlag==true){
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

public void mouseClicked(MouseEvent e){
	if (e.getSource()==allattrlist){
    	rightFlag=true;
    	leftFlag=false;  	
       index=allattrlist.locationToIndex(e.getPoint());
      }
	if (e.getSource()==classattrlist){
    	leftFlag=true;
    	rightFlag=false;   //��һ�Կ���  	
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