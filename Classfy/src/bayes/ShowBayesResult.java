package bayes;

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
     JFrame f=new JFrame("贝叶斯预测结果显示");
     public static final String file="D:/predata/bayes/testpro.txt";
     public static final String testfile="D:/predata/bayes/testpro.xls";
     Image icon = Toolkit.getDefaultToolkit().getImage("D:\\predata\\120.jpg");
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
    //用于JTbale必须在创建之初指定其行数和列数，所以我们只好通过其测试数据的Excel表来获得
  	   try {   
           Workbook book = Workbook.getWorkbook(new File(testfile));
           Sheet sheet = book.getSheet(0);     
           System.out.println("读取文件："+PublicData.getFile()+"行数："+sheet.getRows()+"列数："+sheet.getColumns());
           table=new JTable(sheet.getRows()+1,sheet.getColumns()+1);//创建Excel表总行数行，第一行长度列的JTable
          book.close();
  	   } catch (Exception e) {   
           e.printStackTrace();   
       } 
    //为了能够让预测的列突出，我们需要设置改变其颜色
  	    //新建列表现器------------------------//
       DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
           public Component getTableCellRendererComponent(JTable table,
               Object value, boolean isSelected, boolean hasFocus,
               int row, int column) {
               if (column ==table.getColumnCount()-1)
                   setBackground(Color.cyan); //设置预测列底色
               else
            	   setBackground(Color.white);
               return super.getTableCellRendererComponent(table, value,
                   isSelected,
                   hasFocus, row, column);
           }
       };
   
       
    		BufferedReader read;
    		int j=0;             //代表即将显示的结果的行数
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
                        column.setCellRenderer(tcr);  //添加列表现器	       	
                        table.setValueAt(line[i],j,i);	
                       
                    }
                    j++;
    			}
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.out.println("文件不存在！");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}  	 
    	 return table; 
     }
     
}
