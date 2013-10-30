package Genera_decis_tree;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class FileAndFirstLine extends JFrame implements ActionListener{
    public String file;
    public TextArea tf;
    public String target;
    public String local;
    public CreateXLS createXls=new CreateXLS();
    public PublicData pd;
    public void creatUI(){
 	   Image icon = Toolkit.getDefaultToolkit().getImage("D:\\predata\\120.jpg");
	   this.setIconImage(icon);
    
        this.add(new Label("默认以第一行作为属性名"),"North");
        Panel p1=new Panel();
        p1.setLayout(new GridLayout(1,2));  
        p1.add(new Label("属性列表-以逗号分隔"));
        tf=new TextArea();
        p1.add(tf);
        this.add(p1,"Center");
        
        Panel p2=new Panel();
        Button submit=new Button("提交");
        Button reset=new Button("重置");
        submit.addActionListener(this);
        reset.addActionListener(this);
        p2.add(submit);
        p2.add(reset);
        this.add(p2,"South");
        
        this.setLocation(300,100);
        this.setSize(400,200);
        this.setVisible(true);
          }
    public void UpdateAttr(){
    	String str=null;
    	  try {   
	             Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));// 将所有的数据变化都放在PublicData中   
	             Sheet sheet = book.getSheet(0);                  
	            	  for(int j=0;j<sheet.getColumns();j++){
	            		     Cell cell = sheet.getCell(j, 0);   
	                         String result = cell.getContents();
	                         if(j==0){
	                        	 str=result;
	                         }
	                         else{
	                        	 str=str+","+result;	 
	                         }
                             	  
	            	  }
	             book.close();   
	         } catch (Exception e) {   
	             e.printStackTrace();   
	         }     	
    	PublicData.setAttr(str.split(","));
    }
    
    
    public  static void main(String[] args){
      FileAndFirstLine ff=new FileAndFirstLine();
    	ff.creatUI();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		if(s.equals("提交")){
				PublicData.setAttr(tf.getText().split(","));       //将用户输入的属性列同步到数据中心				
                String[] str=PublicData.getAttr();
                for(int i=0;i<PublicData.getAttr().length;i++){
                      System.out.println(str[i]);	
                }                
                createXls.create(PublicData.getFile(),PublicData.getAttr());   //当提交的时候顺便已成生成相应Excel文件
                //在上面创建Excel文件中已经可以先转换成xls文件
                String[] tempfile=PublicData.getFile().split("\\.");     //以点号为分隔将之前的txt文件分隔,注意点号分隔符的特殊，需要转义
                PublicData.setFile(tempfile[0]+".xls");               //新文件名将会被命名为xls文件
                
                this.dispose();    //只是让当前窗口退出
		}
		else if(s.equals("重置")){
			tf.setText("");
		}
	}
}
