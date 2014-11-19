package JListExample;

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
 *
 */
public class JList8 extends MouseAdapter implements ActionListener{
JList list1=null; 
JList list2=null; 
JList tarlist=null;
DefaultListModel mode1=null;
DefaultListModel mode2=null;
DefaultListModel mode3=null;

JLabel var=new JLabel("变量");
JLabel selvar=new JLabel("自变量");
JLabel tarvar=new JLabel("因变量");
JPanel right=new JPanel();
JButton tarBut=new JButton("设置因变量");
JButton selBut=new JButton("设置自变量");

public static boolean varFlag=false;
public static boolean tarFlag=false;
public static boolean selFlag=false;          //分别用来识别，选择的List项来自哪个list
public static int index=0;                    //整个类公用的 存储当前选择的list项的索引

String[] s = {"美国","日本","大陆","英国","法国","意大利","澳洲","韩国"};

public JList8(){
JFrame f=new JFrame("JList");
Image icon = Toolkit.getDefaultToolkit().getImage("D:\\predata\\120.jpg");
f.setIconImage(icon);

Container contentPane=f.getContentPane();
contentPane.setLayout(new GridLayout(2,2));

tarBut.addActionListener(this);
selBut.addActionListener(this);

mode1=new DataModel(1);
list1=new JList(mode1);
list1.setBorder(BorderFactory.createTitledBorder("国家名称!"));
list1.addMouseListener(this);//一遇到鼠标事件立即执行.

mode2=new DataModel(2);
list2=new JList(mode2);
list2.setBorder(BorderFactory.createTitledBorder("你最喜欢到哪个国家玩呢!"));
list2.addMouseListener(this);//一遇到鼠标事件立即执行.

mode3=new DataModel(3);
tarlist=new JList(mode3);
tarlist.setVisibleRowCount(1);           //设置其显示行数，只显示一行
tarlist.addMouseListener(this);

right.setLayout(new GridLayout(2,2));
right.add(tarBut);
right.add(tarlist);
right.add(selBut);
right.add(new JScrollPane(list2));

contentPane.add(var);
contentPane.add(tarvar);
contentPane.add(new JScrollPane(list1));
contentPane.add(right);
   f.pack();             //让窗口自适应屏幕
   f.setVisible(true);
   f.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
         System.exit(0);
      }
   });    
}
public static void main(String[] args){
new JList8();
}
//处理鼠标键击事件.
public void mouseClicked(MouseEvent e){
    /*对list1而言，当鼠标在某个项目连续按两下时，我们利用JList所提供的locationToIndex()方法，找到所键击的项目，并
     *由tmp取得此项目的项目值，然后将此项目值增加到mode2中[mode2.addElement(tmp)],用setModel重新设置list2的
     *ListModel,使list2可显示出所增加的项目，将刚刚在list1双击的项目删除.
     */
if (e.getSource()==list1){
    	varFlag=true;
    	selFlag=false;
    	
       index=list1.locationToIndex(e.getPoint());
}
if (e.getSource()==list2){
    	selFlag=true;
    	varFlag=false;   //是一对开关
    	
       index=list2.locationToIndex(e.getPoint());
 }
if(e.getSource()==tarlist){
	tarFlag=true;
	varFlag=false;
	
	index=tarlist.locationToIndex(e.getPoint());
}
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
	if(s.equals("设置因变量")&&(tarlist.getModel().getSize()==0)&&varFlag==true){            //向目标变量List中添加结点
		String tmp=(String)mode1.getElementAt(index);
	    System.out.println("添加问题"); 
		 mode3 .addElement(tmp);
	     tarlist.setModel(mode3);
	     mode1.removeElementAt(index);
	     list1.setModel(mode1);
	}
	else if(s.equals("设置因变量")&&tarlist.getModel().getSize()>0&&tarFlag==true){   //从目标变量List中移除结点
		String tmp=(String)mode1.getElementAt(index);
		System.out.println("移除问题");
		mode1.addElement(tmp);
		list1.setModel(mode1);
		mode3.removeElementAt(index);
		tarlist.setModel(mode3);
	}
	else if(s.equals("设置自变量")&&varFlag==true){
		String tmp=(String)mode1.getElementAt(index);
		mode2.addElement(tmp);
		list2.setModel(mode2);
		mode1.removeElementAt(index);
		list1.setModel(mode1);
	}
	else if(s.equals("设置自变量")&&selFlag==true){
		String tmp=(String)mode2.getElementAt(index);
		mode1.addElement(tmp);
		list1.setModel(mode1);
		mode2.removeElementAt(index);
		list2.setModel(mode2);	
	}
	
}
}