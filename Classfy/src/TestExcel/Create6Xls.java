package TestExcel;
import java.io.*;   
import jxl.*;   
import jxl.write.*;   
  
/**
* 默认创建d:/Temp.xls文件
*/
public class Create6Xls {  
   
	    private static int j=0;      //在读取文件过程中，控制行数的i不断的从0开始，导致j会始终为0
	    public void create(String name,String[] Attr){ 
	        try {   
	            //新建文件名
	             WritableWorkbook book = Workbook.createWorkbook(new File("D:/数据挖掘/数据挖掘数据/6/nursey.xls"));     
	             WritableSheet sheet = book.createSheet("Sheet_1", 0);                 
	             try{          	    
	        		   FileReader fr=new FileReader(name);
	        		   BufferedReader br=new BufferedReader(fr);
	        		   String s;
	        	       while((s=br.readLine())!=null)
	          		 {
	        	     String[] msg=new String(s).trim().split(","); 
	          	     for(int i=0;i<msg.length;i++)
	          	     { 
	          	    	 if(j==0)
	          	    	 {
	          	    		 for(int m=0;m<Attr.length;m++)
	          	    		 {
	          	    			 Label lab=new Label(m,0,Attr[m]);
	          	    			 sheet.addCell(lab);
	          	    		 }                  //创建第一列属性列
	          	    		 j++;
	          	    	 }	 
	          	        else if(j!=0&&i!=0&&i%Attr.length==0)
	       	                      {   j++;  }
	          	        else if(j!=0&&i%Attr.length==0)
	          	        	j++;
	          		 System.out.println("j="+j);
	                  Label label = new Label(i, j, msg[i]);
	                  sheet.addCell(label); 	    	         	 
	          	      }
	          	  } 
	          	 }catch(IOException e){
	          				e.printStackTrace();
	          				System.out.println("cannot find file");
	          			}    
	             book.write();     
	             book.close();   
	         } catch (Exception e) {   
	             e.printStackTrace();   
	         }   
	     }   
	} 
