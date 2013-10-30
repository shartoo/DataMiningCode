


import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Genera_decis_tree.DataScanPane;
import Genera_decis_tree.FileAndFirstLine;
import Genera_decis_tree.OuterFace;
import Genera_decis_tree.PublicData;

public class MyMonitor implements ActionListener,MouseListener,MouseMotionListener{
	  FileAndFirstLine ffl=new FileAndFirstLine();
	  PublicData pd=new PublicData();
	  private Frame frame=new Frame("文件操作");
	  private FileDialog fd_load=new FileDialog(frame,"打开文件",FileDialog.LOAD);
	  private FileDialog fd_save=new FileDialog(frame,"保存文件",FileDialog.SAVE);
	   String file="";
	   OuterFace ot=new OuterFace();
	   DataScanPane dsp=new DataScanPane();
	public void actionPerformed(ActionEvent e){
		String s=e.getActionCommand();
		if(s.equals("退出")){
			System.exit(0);
		}
		if(s.equals("决策树"))
		{
			System.out.println("决策树测试小牧");
			Attri_select_method asm=new Attri_select_method();
			asm.createUI();
		}
		
		if(s.equals("打开"))
		{
		  fd_load.setVisible(true);
		  String d=fd_load.getDirectory();
		  String f=fd_load.getFile();
		  if((d!=null)&&(f!=null)){
				 file=d+f;				 	
				 if(file.endsWith(".txt")){
					 ffl.creatUI();             //如果操作的是txt文件，需要手动指定属性名称
					 PublicData.setFile(file);    //同时将文件名设置成当前的，不过此时文件名是txt文件，
					                            //将会在ffl的窗口界面点击提交再次被重命名为xls文件
				 System.out.println(PublicData.getFile());
				 }
				 else if(file.endsWith(".xls")){
					 PublicData.setFile(file);
		
				 }
			   }
		   }
		//在开发初期，没有定义数据格式，无法处理保存和另存为数据，所以基本没用
		 else if(s.equals("另存为"))
		 {		   
		   fd_save.setVisible(true);
		   String d=fd_save.getDirectory();
		   String f=fd_save.getFile();
		   if((d!=null)&&(f!=null))
		         {
				   file=d+f;					
				   frame.setTitle("文件另存为"+file);
				   }
		  }
		 else if(s.equals("保存")){
			   try{
				   PrintWriter pw=new PrintWriter(new FileWriter(file));
				   pw.close();
			   }catch(IOException e1){
				   e1.printStackTrace();
			   }
	 
		 }
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseMoved(MouseEvent e) {	 
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}