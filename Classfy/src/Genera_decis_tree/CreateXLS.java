package Genera_decis_tree;
import java.io.*;   
import jxl.*;   
import jxl.write.*;   
import jxl.write.Number;
  
/**
* 默认创建d:/predata/Temp.xls文件
* 在创建Excel文件的同时把它同步到公共数据中心,并将File设置成默认创建的文件名
*/  
public class CreateXLS {   
    private static int j=0;      //在读取文件过程中，控制行数的i不断的从0开始，导致j会始终为0
    public void create(String name,String[] Attr){ 
        try {   
            //新建文件名
        	String[] n=name.split("\\.");        //根据当前传入的txt文件，再该文件的当前目录下生成同名的Excel文件
        	String xls=n[0]+".xls";
             WritableWorkbook book = Workbook.createWorkbook(new File(xls));   
             WritableSheet sheet = book.createSheet("Sheet_1", 0);                 
             try{                 	   
        		   FileReader fr=new FileReader(name);
        		   BufferedReader br=new BufferedReader(fr);
        		   String s;
        		   //首先写入Excel表的第一行辨别行
        			 for(int m=0;m<Attr.length;m++)
      	    		 {
      	    			 Label lab=new Label(m,0,Attr[m]);
      	    			 sheet.addCell(lab);
      	    		 }                  //创建第一列属性列   
      	           int column=0;   //控制文本的index，必须在readline之前。   
        	       while((s=br.readLine())!=null)
          	       {
        	         String[] msg=new String(s.replaceAll("\"", "")).trim().split(","); //以逗号为分隔符
        	         //String[] msg=s.split("[\\t \\n]+");                                         //以空格为分隔符号
          	        for(int i=0;i<msg.length;i++)
          	        {
          	          if(i%Attr.length==0)
       	                {   j++;
       	                   column=0;    //同时index变为0
       	                }
          	           
          	      // WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false);//指定其格式
     
                  Label label = new Label(column, j, msg[i]);    
                  column++;
                  sheet.addCell(label);            
          	     }
          	} 
          }catch(IOException e){
          				e.printStackTrace();
          			}    
             book.write();     
             book.close();   
         } catch (Exception e) {   
             e.printStackTrace();   
         }   
     }   
} 