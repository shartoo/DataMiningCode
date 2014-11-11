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
     JLabel selAttr=new JLabel("选择属性");
     JLabel tip=new JLabel("聚类中心数目");
     JButton setAttr=new JButton("设置属性");
     JButton submit=new JButton("提交");
     final Choice clusterNum = new Choice();    //�û�ѡ��������
     
     JScrollPane allPane=new JScrollPane();
     JScrollPane selPane=new JScrollPane();
     Boolean right=false;   //�������ƿ���
     Boolean left=false;    //�������ƿ���
     public int index=0;    //������JList�����Ե�����
     public int cluNum=0;
     final JFrame f=new JFrame("快速均值聚类设置");
     public String[] all=PublicData.getAttr();   //��ȡ��ݱ��е���������
     
     public void CreateUI()
     {   
         setAttr.addActionListener(this);
         submit.addActionListener(this);
         clusterNum.addItemListener(new ItemListener()
         {
        	 public void itemStateChanged(ItemEvent arg0) 
        	   {  		 
        		  cluNum=clusterNum.getSelectedIndex()+2;
        		  System.out.println("均值聚类属性设置"+cluNum+"--------itemStateChanged");
        	      submit.addMouseListener(new MouseListener(){
        	      public void mouseClicked(MouseEvent arg0) 
        	      {
        	             // TODO �Զ���ɷ������
        	      }
        	      public void mouseEntered(MouseEvent arg0) 
        	      {
        	         // TODO �Զ���ɷ������
        	      }
        	      public void mouseExited(MouseEvent arg0) 
        	      {
        	         // TODO �Զ���ɷ������
        	      }
        	     public void mousePressed(MouseEvent arg0) 
        	      {
        	        // TODO �Զ���ɷ������
        	      }
        	     public void mouseReleased(MouseEvent arg0) 
        	     {
        		   cluNum=clusterNum.getSelectedIndex()+2;}
        	    });
        	 }
        	 });     
         //���2--10�ľ������
         for(int i=0;i<9;i++)
         {
        	 String s=i+2+"";
        	 clusterNum.addItem(s);
         }              
         Image icon = Toolkit.getDefaultToolkit().getImage("img/logo.jpg");
     	 f.setIconImage(icon);
     	//Container contentPane=f.getContentPane();
    	//contentPane.setLayout(new GridLayout(3,3));
    	f.setLayout(new GridLayout(3,3));
     	modeAll=new DataModel(1);
    	allList=new JList(modeAll);
    	allList.setBorder(BorderFactory.createTitledBorder("数据全部属性列表"));
    	allList.addMouseListener(this);//һ��������¼�����ִ��.

    	modeSel=new DataModel(2);
    	selList=new JList(modeSel);
    	selList.setBorder(BorderFactory.createTitledBorder("需要聚类分析属性"));
    	selList.addMouseListener(this);//һ��������¼�����ִ��.
    	 
    	allPane.setViewportView(allList);
    	selPane.setViewportView(selList);
    	f.add(allAttr);
    	f.add(new JLabel(""));   //����հױ�ǩ���γ�3*3�����
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
		//����JList������
		if(s.equals("设置属性")&&right==true)
		{  
			String tmp=(String) modeAll.getElementAt(index);
			 modeSel .addElement(tmp);
		     selList.setModel(modeSel);
		     modeAll.removeElementAt(index);
		     allList.setModel(modeAll);
			
		}
		//����JList������
		else if(s.equals("设置属性")&&left==true)
		{  
			String tmp=(String) modeSel.getElementAt(index);
			 modeAll .addElement(tmp);
		     allList.setModel(modeAll);
		     modeSel.removeElementAt(index);
		     selList.setModel(modeSel);
			
		}	
		else if(s.equals("提交")){
			System.out.println("用户选择了聚类中心数目"+this.getclusterNum());
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
			  PublicData.setClusterAttr(clusterAttr);     //���û�ѡ������ھ�������Ը��µ��������
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

	public void mouseClicked(MouseEvent e){
		if (e.getSource()==allList){
	    	right=true;
	    	left=false;
	       index=allList.locationToIndex(e.getPoint());
	      }
		if (e.getSource()==selList){
	    	left=true;
	    	right=false;   //��һ�Կ���  	
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
		System.out.println("�û�ѡ��ľ��������(���������---)"+ssa.getclusterNum());
	}
   */
}
