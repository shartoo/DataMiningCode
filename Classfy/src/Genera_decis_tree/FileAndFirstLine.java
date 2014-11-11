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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
    
        this.add(new JLabel("设置文件数据中属性名称列表"),"North");
        Panel p1=new Panel();
        p1.setLayout(new GridLayout(1,2));  
        p1.add(new JLabel("默认以逗号分隔"));
        tf=new TextArea();
        p1.add(tf);
        this.add(p1,"Center");
        
        Panel p2=new Panel();
        JButton submit=new JButton("提交");
        JButton reset=new JButton("重置");
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
	             Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));// �����е���ݱ仯������PublicData��   
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
				PublicData.setAttr(tf.getText().split(","));       //���û������������ͬ�����������				
                String[] str=PublicData.getAttr();
                for(int i=0;i<PublicData.getAttr().length;i++){
                      System.out.println(str[i]);	
                }                
                createXls.create(PublicData.getFile(),PublicData.getAttr());   //���ύ��ʱ��˳���ѳ������ӦExcel�ļ�
                //�����洴��Excel�ļ����Ѿ�������ת����xls�ļ�
                String[] tempfile=PublicData.getFile().split("\\.");     //�Ե��Ϊ�ָ���֮ǰ��txt�ļ��ָ�,ע���ŷָ�������⣬��Ҫת��
                PublicData.setFile(tempfile[0]+".xls");               //���ļ���ᱻ����Ϊxls�ļ�
                
                this.dispose();    //ֻ���õ�ǰ�����˳�
		}
		else if(s.equals("重置")){
			tf.setText("");
		}
	}
}
