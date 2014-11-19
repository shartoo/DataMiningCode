package OuterFace;
import java.io.*;   
import java.util.Scanner;
import jxl.*;   
import jxl.write.*;   
public class CreatExcel {
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
     jxl.write.Label labelC = new jxl.write.Label(0, 0, "性别", wcfF);
     jxl.write.Label label1 = new jxl.write.Label(1, 0, "差异", wcfF);
     jxl.write.Label label2 = new jxl.write.Label(2, 0, "种族", wcfF);
     jxl.write.Label label3 = new jxl.write.Label(3, 0, "状况", wcfF);
     jxl.write.Label label4 = new jxl.write.Label(4, 0, "贫困", wcfF);
     jxl.write.Label label5 = new jxl.write.Label(5, 0, "地域", wcfF);
     jxl.write.Label label6 = new jxl.write.Label(6, 0, "天气", wcfF);
     jxl.write.Label label7 = new jxl.write.Label(7, 0, "人权", wcfF);
     jxl.write.Label label8 = new jxl.write.Label(8, 0, "基尼指数", wcfF);
     sheet.addCell(labelC);
     sheet.addCell(label1);
     sheet.addCell(label2);
     sheet.addCell(label3);
     sheet.addCell(label4);
     sheet.addCell(label5);
     sheet.addCell(label6);
     sheet.addCell(label7);
     sheet.addCell(label8);
    try{
    
		   FileReader fr=new FileReader(name);
		   BufferedReader br=new BufferedReader(fr);
		   String s;
		   int j=1; 
	       while((s=br.readLine())!=null)
  		 {
			 String[] msg=new String(s).trim().split(",");  
			 for(int i=0;i<msg.length;i++)
			 {
				 System.out.print(msg[i]+"\t");
                jxl.write.Label labelCF = new jxl.write.Label(i, j, msg[i], wcfF);//参数中第一个代表列数，第二个代表行数，最后一个代表字体
                sheet.addCell(labelCF);
			 }
		    j++;        //j用来控制行数
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
