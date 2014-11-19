package Genera_decis_tree;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
/**
 * 必选先向公共数据中心PublicData设置好文件值才能执行
 * 方法一：getTable传入参数：Excel表属性字段数组
 * 返回一个JTabel
 * @author Administrator
 *
 */
public class DataScanPane {
	JTable table;
	 public  JTable getTable(){
		 String str="";
			   try {   
		             Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));// 将所有的数据变化都放在PublicData中   
		            //get a Sheet object.   
		             Sheet sheet = book.getSheet(0);     
		             System.out.println("读取文件："+PublicData.getFile()+"行数："+sheet.getRows()+"列数："+sheet.getColumns());
		            table=new JTable(sheet.getRows(),sheet.getColumns());//创建Excel表总行数行，第一行长度列的JTable
		             for(int i=0;i<sheet.getRows();i++)
		             {
		            	 for(int j=0;j<sheet.getColumns();j++){
		            		 if(i==0){
		                       str=str+sheet.getCell(j, i).getContents()+",";		
		            		 }
		            		     Cell cell = sheet.getCell(j, i);   
		                         String result = cell.getContents().replaceAll("\"", "").trim();	
		     	    			 table.setValueAt(result,i,j);    		  
		            	  }
		             }
		             book.close();   
		         } catch (Exception e) {   
		             e.printStackTrace();   
		         }     
		 	  table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 PublicData.setAttr(str.split(","));                      //直接读取的Excel文件必须也要同步到数据中心
		 return table;
	 }
}
