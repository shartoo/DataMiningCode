package com.datamining.bayes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import jxl.Sheet;
import jxl.Workbook;
import Genera_decis_tree.PublicData;

public class ShowBayesResult {
	 TrainData train=new TrainData();
     JFrame f=new JFrame("贝叶斯预测");
     public static final String file="Data/bayes/testpro.txt";
     public static final String testfile="Data/bayes/testpro.xls";
     Image icon = Toolkit.getDefaultToolkit().getImage("img/logo.jpg");
     public void show()
     {
       train.testPro(); 
       f.setIconImage(icon);
       f.setLocation(100,200);
       f.getContentPane().add(this.getData());
       f.setVisible(true);
       f.setSize(f.getPreferredSize());
     }
     public JTable getData(){
    	 JTable table = null;
    //����JTbale�����ڴ���֮��ָ���������������������ֻ��ͨ���������ݵ�Excel�������
  	   try {   
           Workbook book = Workbook.getWorkbook(new File(testfile));
           Sheet sheet = book.getSheet(0);     
           System.out.println("使用文件"+PublicData.getFile()+"有多少行"+sheet.getRows()+"多少列"+sheet.getColumns());
           table=new JTable(sheet.getRows()+1,sheet.getColumns()+1);//����Excel���������У���һ�г����е�JTable
          book.close();
  	   } catch (Exception e) {   
           e.printStackTrace();   
       } 
    //Ϊ���ܹ���Ԥ�����ͻ����������Ҫ���øı�����ɫ
  	    //�½��б�����------------------------//
       DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
           public Component getTableCellRendererComponent(JTable table,
               Object value, boolean isSelected, boolean hasFocus,
               int row, int column) {
               if (column ==table.getColumnCount()-1)
                   setBackground(Color.cyan); //����Ԥ���е�ɫ
               else
            	   setBackground(Color.white);
               return super.getTableCellRendererComponent(table, value,
                   isSelected,
                   hasFocus, row, column);
           }
       };
   
       
    		BufferedReader read;
    		int j=0;             //��?����ʾ�Ľ�������
    		String f=null;
    		try {
    			read = new BufferedReader(new FileReader(file));
    			System.out.println(f);
    			while((f=read.readLine())!=null)
    			{
    				String[] line=f.split(",");
    				System.out.println("line长度"+line.length);
    				
                    for(int i=0;i<line.length;i++)
                    {
                        TableColumn column = table.getColumn(table.getColumnName(i));
                        column.setCellRenderer(tcr);  //����б�����	       	
                        table.setValueAt(line[i],j,i);	
                       
                    }
                    j++;
    			}
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.out.println("找不到文件");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}  	 
    	 return table; 
     }
     
}
