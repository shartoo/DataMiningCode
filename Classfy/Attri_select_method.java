
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Genera_decis_tree.CreateXLS;
import Genera_decis_tree.DataScanPane;
import Genera_decis_tree.InformationGain;
import Genera_decis_tree.PublicData;
import Genera_decis_tree.ShowTreePane;
/**
 * 数据表换时需要修改的代码部分：
 * InformationGain中c2的辨别条件（yes|recomm。。。。）
 * 本类中的ing.getInformationGain(temp, (String[])attr, 8, 6); 中的属性列和类别列
 * @author Shartoo
 *
 */
public class Attri_select_method{
     static CreateXLS cls=new CreateXLS();    // 将读入的文本文件传入，转换成Excel文件
     static DataScanPane dsp=new DataScanPane();
     Object data[][]={};     
     static Object[] attr;
	 static InformationGain ing=new InformationGain();     //计算信息增益类
	// static ShowStatis ss=new ShowStatis();
     public void createUI(){  	 
    	 attr=PublicData.getAttr();          //调用GetArrayList获取属性字段数组
    	 
    	 ShowTreePane stp=new ShowTreePane();
    	 stp.setPreferredSize(new Dimension(400,200));
    	/** 
    	 TestPane tp=new TestPane();
    	 tp.setPreferredSize(new Dimension(200,200));//不设置其PerferedSize值，若JPanel边界大于（100,100）就不能正常显示
    	 JScrollPane jsp=new JScrollPane(tp);          //不用JScrollPanel就不能显示出TP
    	 
    	 jsp.setViewportView(stp);
    	 */
         Scanner sc=new Scanner(System.in);
   	     System.out.println("请指定源文件（文本格式）！");
    	 String name=sc.next();
    	  
    	  //先创建Excel文件，再读取Excel文件，其中的Sum_Of_Attr用来指定列数
          cls.create(name,(String[])attr);
          //测试信息增益是否得到
          String temp="d:/Temp.xls";
          //for(int i=0;i<attr.length;i++)
          //{
        //	  System.out.println("--------------");
       // 	  System.out.println("属性"+attr[i]+"的信息增益为："
       //	  +ing.getInformationGain(temp, (String[])attr, 4, i));
        //	  ing.clear();
       //  }
          
          ing.getInformationGain(temp, (String[])attr, 8, 6); 
          //ss.setAttr(ing.getList());
          //ss.setNum(ing.getValue());
          //ss.createUI();
          ing.clear();
    	  JFrame jf=new JFrame("数据浏览");
    	  JScrollPane pane=new JScrollPane(dsp.getTable());
    	 
    	  jf.add("Center",pane);
    	 // jf.add(jsp,"East");
    	  jf.setSize(500,600);
    	   
    	  jf.addWindowListener(new WindowAdapter(){
    		  public void windowClosing(WindowEvent e){
    			  System.exit(0);
    		  }
    	  });
    	  jf.setVisible(true);
    	 
     }
}   
