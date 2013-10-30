package TestExcel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import jxl.*;   
import jxl.write.*;   
public class CreatExcel {
	public static int i=0;
	public static void main(String[] args){
		
		Scanner scan=new Scanner(System.in);
    	 String name,excelpath;
    	 System.out.println("请指定要转化的文件！");
    	 name=scan.next();
    	 System.out.println("请指定被转化后的文件名（绝对路径下：D:/myfile/data.xls）！");
    	 excelpath=scan.next();
	
	try{
	                      //新建文件名
		
       WritableWorkbook book = Workbook.createWorkbook(new File(excelpath));   
                           //create Sheet named "Sheet_1". 0 means this is 1st page.   
      WritableSheet sheet = book.createSheet("UCI数据", 0);
     jxl.write.WritableFont wf = new jxl.write.WritableFont(WritableFont.TIMES, 18, WritableFont.BOLD, true);
     jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wf);
     
     jxl.write.Label label0 = new jxl.write.Label(0, 0, "workclass", wcfF);
     jxl.write.Label label1 = new jxl.write.Label(1, 0, "education-num", wcfF);
     jxl.write.Label label2 = new jxl.write.Label(2, 0, "education", wcfF);
     jxl.write.Label label3 = new jxl.write.Label(3, 0, "fnlwgt", wcfF);
     jxl.write.Label label4 = new jxl.write.Label(4, 0, "marital-status", wcfF);
     jxl.write.Label label5 = new jxl.write.Label(5, 0, "occupation", wcfF);
     jxl.write.Label label6 = new jxl.write.Label(6, 0, "relationship", wcfF);
     jxl.write.Label label7 = new jxl.write.Label(7, 0, "race", wcfF);
     jxl.write.Label label8 = new jxl.write.Label(8, 0, "sex", wcfF);
     jxl.write.Label label9 = new jxl.write.Label(9, 0, "capital-gain", wcfF);
     jxl.write.Label label10 = new jxl.write.Label(10, 0, "capital-loss", wcfF);
     jxl.write.Label label11= new jxl.write.Label(11, 0, "age", wcfF);
     jxl.write.Label label12 = new jxl.write.Label(12, 0, "native-country", wcfF);
     jxl.write.Label label13 = new jxl.write.Label(13, 0, "hours-per-week", wcfF);
     //jxl.write.Label label14 = new jxl.write.Label(13, 0, "detail-sarlery", wcfF);
     sheet.addCell(label0);
     sheet.addCell(label1);
     sheet.addCell(label2);
     sheet.addCell(label3);
     sheet.addCell(label4);
     sheet.addCell(label5);
     sheet.addCell(label6);
     sheet.addCell(label7);
     sheet.addCell(label8);
     sheet.addCell(label9);
     sheet.addCell(label10);
     sheet.addCell(label11);
     sheet.addCell(label12);
     sheet.addCell(label13);
    // sheet.addCell(label14);
     System.out.println("problem not here");
     
    try{
    
		   FileReader fr=new FileReader(name);
		   BufferedReader br=new BufferedReader(fr);
		   String s;
		   int j=1,k; 
	       while((s=br.readLine())!=null)
   		 {
	    	   k=1;
			 String[] msg=new String(s).split(",");  
			 
			 for(i=0;i<msg.length&&k<=16;i++,k++)
			 {
				 System.out.print(msg[i]+"\t");
                jxl.write.Label labelCF = new jxl.write.Label(i, j, msg[i], wcfF);//参数中第一个代表列数，第二个代表行数，最后一个代表字体
                sheet.addCell(labelCF);
			 }	 
		    j++;        //j用来控制行数
		    System.out.println(msg.length);
  		 }	 
	}catch(IOException e){
		e.printStackTrace();
	}
     book.write();     
     book.close();   
  }catch (Exception e) {   
      e.printStackTrace();   
  }   
}

	
}
