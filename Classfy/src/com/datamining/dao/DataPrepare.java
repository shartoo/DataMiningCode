package com.datamining.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import com.datamining.constant.CommonConstant;
/**
 * 
 * @author  夏涛
 * email��shartoo518@gmail.com
 * 将Excel数据映射到mysql
 */
public class DataPrepare {
 /**
  * 所有用户需要分析的数据都要暂存到这个数据库表中
  */
public final String tmpTable="analyTable";
/**
 * 批量插入数据记录的数目，默认每10万条提交一次
 */
public final int BATCH_SIZE=100000;
/**
 * 处于内存考虑，从txt文本中读取记录一次性保存在一个数组中的行数。
 */
public final int TXT_LINE_SIZE=100000;

 /**
  * 将用户输入的Excel数据映射到相应的mysql数据库中
  * @param file 需要分析的Excel表的完整路径名
  */
 public void excelToDB(String file){
	
	 /**
	  * 根据初始用户的Excel表建立对应的数据库表。
	  * 第一步，根据Excel列数量建立表。建立前查看如果存在表需要先删除
	  */
	 try {   
         Workbook book = Workbook.getWorkbook(new File(file)); 
         
         Sheet sheet = book.getSheet(0);  
         
         GetMySqlConnect connect=new GetMySqlConnect();
         
         Connection conn=connect.getConnection();
         
         int column=sheet.getColumns();
         
         String dropTable = "drop table if exists "+tmpTable;
         
         String createTable="create table  if not exists "+tmpTable+"(";   
         
         for(int i=0;i<column;i++)
         {
        	createTable+=sheet.getCell(i,0).getContents(); 
        	if(i!=column-1)
        	    createTable+=" varchar(200),";
        	else
        		createTable+=" varchar(200)";
         }
         createTable+=");";
         Statement st=conn.createStatement();
         st.execute(dropTable);
         st.execute(createTable);
         /**
          * 第二步，根据Excel中表格数据批量插入到数据库中
          */
         //写好预处理语句
         String insert_sql="insert into "+tmpTable+" values(";
         for(int j=0;j<column-1;j++)
         {
        	insert_sql+="?,";
         }
         insert_sql+="?)";      //上一步中少添加 一个占位符 ？
         
         PreparedStatement pst=conn.prepareStatement(insert_sql);
  
         long startTime = System.currentTimeMillis();  
         
         try {  
             int count = 0;  
              
             for(int i = 1; i < sheet.getRows()-2; i++)
             {  
            	 for(int j=0;j<column ;j++)
            	 {
            		  pst.setString(j+1, sheet.getCell(j,i).getContents()); 
            		  
            	 }   
            	 conn.setAutoCommit(false);   
                 pst.addBatch(); 
                 count ++;  
                 
                 if(count >= BATCH_SIZE)
                 {  
                     count = 0;  
                     pst.executeBatch();  
                     conn.commit();  
                 }  
             }  
             
            pst.executeBatch();  
            conn.commit();  
            conn.close();
         System.out.println( "插入数据花费时间是："+(System.currentTimeMillis() - startTime) + "ms");  
              
         } catch (SQLException e) {  
             e.printStackTrace();  
         } 
         book.close();   
     } catch (Exception e) {   
         e.printStackTrace();   
     }   	
	 
 }
	
 /**
  * 将用户输入的txt文本导入到数据库中
  * @param file               用户需要分析的txt文本路径名
  * @param splitStr           每个属性值之间的分割符号，比如“a,12,hot.....”分割符号为“,”
  * @param changeLineStr      换行符，也即一条记录完结时的结尾符号。默认为txt文本中换行符
  */
 public void txtToDB(String file,String splitStr,String changeLineStr){
	//在我们开始插入数据之前必须先确定 数据的所有属性列表已经获取到，否则无法建立相应表
	 CommonConstant constant=new CommonConstant();
	 
	 GetMySqlConnect connect=new GetMySqlConnect();
     
     Connection conn=connect.getConnection();
     
     String dropTable = "drop table if exists "+tmpTable;
     
     String createTable="create table  if not exists "+tmpTable+"(";   
     
	 ArrayList<String> list=constant.getAttrNameList();
	 
	 if(list==null||list.size()==0)
	 {
		 constant.setWarnMessage("请先指定分析表的属性名称列表！");
	 }
	 else
	 {

         for(int i=0;i<list.size();i++)
         {
        	createTable+=list.get(i); 
        	if(i!=list.size()-1)
        	    createTable+=" varchar(200),";
        	else
        		createTable+=" varchar(200)";
         }
         createTable+=");";
         try
         {
        //建立表
         Statement st=conn.createStatement();
         st.execute(dropTable);
         st.execute(createTable);
         //批两插入预处理语句
         String insert_sql="insert into "+tmpTable+" values(";
         for(int j=0;j<list.size()-1;j++)
         {
        	insert_sql+="?,";
         }
         insert_sql+="?)";      //上一步中少添加 一个占位符 ？
         
         PreparedStatement pst=conn.prepareStatement(insert_sql);
         
		 if(changeLineStr==""||changeLineStr.equals("")||changeLineStr.length()==0)
		 {
			BufferedReader read;
			String f=null;
			int num=0;
			try 
			{
			 read = new BufferedReader(new FileReader(file));
			 ArrayList<String> cacheList=new ArrayList<String>();
			 f=read.readLine();
			 num++;
			 //批量插入
			 if(num!=TXT_LINE_SIZE)
				 cacheList.add(f);
			 else
			 {
			   num=0;
			   for(int i=0;i<cacheList.size();i++)
				{
				  String[] oneLine=cacheList.get(i).split(splitStr);
				  for(int j=0;j<oneLine.length;j++)
				  {
					pst.setString(j+1,oneLine[j]);
				  }
				  conn.setAutoCommit(false);   
	              pst.addBatch();	 
			   } 
			  pst.executeBatch();  
		      conn.commit();  
			  cacheList.clear(); 
			 }		 
			 conn.close();
			} 
			catch (FileNotFoundException e) 
			   {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("文件路径错误！");
				}
			    catch (IOException e) 
			    {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
         }
		 
		 else
		 {
			 
			 
		 }
	 }
       catch (SQLException e) {  
         e.printStackTrace();  
     } 
	}
	 
 }
 public void test(){
	 CommonConstant constant=new CommonConstant();
	 constant.setUserFile("Data/测试数据.xls");
	 excelToDB(constant.getUserFile());
 }
	public static void main(String[] args){
		DataPrepare p=new DataPrepare();
		p.test();
	}
}
