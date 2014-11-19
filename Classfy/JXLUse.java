/**
 * * 【WorkbookParser/Workbook】类： 
 * WorkbookParser.getWorkbook(InputStream 对象)：创建一个WorkbookParser对象
 * getSheets()：返回工作薄（Workbook）中工作表（Sheet）对象数组 
 * getSheet(int i)：获得指定的sheet对象,i从0开始
 * getNumberOfSheets()：获得工作薄中工作表sheet的个数 
 * 【WritableWorkbook】类：创建可写入的工作簿对象
 * Workbook.createWorkbook(File对象)：创建一个WritableWorkbook对象
 * createSheet(String arg0,int arg1):arg0,sheet的名称，arg1表示第几个sheet，下标从0开始
 * 【Sheet】类： 
 * getName()：获得sheet的名称
 * getColumns(0)：获取某一列所有的单元格
 * 【WritableSheet】类：
 * 【Cell】类：
 * 【WritableCell】类：
 * 【CellType】类：
 * 【Label】类：
 * 【NumberFormat】类：
 * 【WritableCellFormat】类：
start
导入jar，新建一个普通的java类
 * @author Administrator
 *
 */
   import java.io.File;
   import java.io.FileInputStream;
   import java.io.FileNotFoundException;
   import java.io.FileOutputStream;
   import java.io.OutputStream;
   //import java.io.FileOutputStream;
   import java.io.IOException;
  import java.io.InputStream;
  //import java.io.OutputStream;
 import java.util.Date;
 import jxl.*;
public class JXLUse {
	 public static void main(String[] args) throws RowsExceededException,
	             WriteException, IOException, BiffException {
	         String path = "src//com//office//test.xls";
	          writeDataToExcel(path);
	          getExcelData(path);
	  
	      }
 }  
	     // 利用jxl.jar包取excle表格的数据
	     public static void getExcelData(String path) throws BiffException,
	            IOException {
	         InputStream is = new FileInputStream(path);
	        //Workbook wb = null;
	          WorkbookParser rwb = (WorkbookParser) WorkbookParser.getWorkbook(is);
	         System.out.println("获得工作薄中工作表sheet的个数=" + rwb.getNumberOfSheets());
	         @SuppressWarnings("unused")
	         Sheet[] sheets = rwb.getSheets();// 返回工作薄（Workbook）中工作表（Sheet）对象数组
	       Sheet rs = rwb.getSheet(0);
        System.out.println("获得sheet的名称=" + rs.getName());
	        System.out.println("获得sheet中所包含的总列数=" + rs.getColumns());
	         @SuppressWarnings("unused")
         Cell[] cells1 = rs.getColumn(0);// 获取某一列所有的单元格
	        System.out.println("获得sheet中所包含的总行数=" + rs.getRows());
	         @SuppressWarnings("unused")
	        Cell[] cells2 = rs.getRow(0);// 获取某一行的所有单元格
	
        Cell c00 = rs.getCell(0, 0);// 第一个是列数，第二个是行数
	        String t = c00.getContents();// getContents()将任何类型的Cell值都作为一个字符串返回
	         System.out.println(t);
	         if (c00.getType() == CellType.LABEL) {
	             LabelCell labelc00 = (LabelCell) c00;
	             String strc00 = labelc00.getString();
	             System.out.println(strc00);
	        }
	        if (c00.getType() == CellType.NUMBER) {
	             NumberCell number00 = (NumberCell) c00;
	            double strc00 = number00.getValue();
	             System.out.println(strc00);
	         }
	         if (c00.getType() == CellType.DATE) {
	            DateCell datec00 = (DateCell) c00;
	            Date strc00 = datec00.getDate();
	             System.out.println(strc00);
           }
	         rwb.close();
	        is.close();
	    }
	 
	     // 利用jxl.jar包向excle表格写数据
	     public static void writeDataToExcel(String path) throws IOException,
	             RowsExceededException, WriteException {
	        File file = new File(path);
	         // 构造Workbook对象，只读Workbook对象
	// Method1:创建可写入的excle工作簿
	        WritableWorkbook wwb1 = Workbook.createWorkbook(file);
	         // Method2:将WritableWorkbook直接写入到输出流
	 //OutputStream os = new FileOutputStream(path);
	 //WritableWorkbook wwb2 = Workbook.createWorkbook(os);
	 // 创建Excel工作表
	          WritableSheet ws = wwb1.createSheet("测试", 0);//arg0,表示sheet的名称,arg1表示第几个sheet，下标从0开始
	  
	  //设置列的宽度、设置行的高度 jxl中20个高度对应excel的1个高度，jxl的1个宽度对应excel的7个宽度
	         ws.getSettings().setDefaultColumnWidth(25); //设置列宽，所有列
	         ws.getSettings().setDefaultRowHeight(600); //设置行高，所有行
	 
	         ws.setColumnView(0, 20);//设置列宽，第一个参数为第几列，第二个参数为列宽
	         ws.setRowView(0, 800);//设置行高，第一个参数为第几行，第二个参数为行高
	 
	 //是否显示网格 默认为true显示网格
	         ws.getSettings().setShowGridLines(true);
	 
         // (1)添加Label对象
         Label labelC = new Label(0, 0, "Label单元格");
	         ws.addCell(labelC);

	        // (2) 创建带有字型Formatting的对象  
         WritableFont wf = new WritableFont(WritableFont.TIMES, 18,
                 WritableFont.BOLD, true);
         WritableCellFormat wcfF = new WritableCellFormat(wf);
         //下面Label构造函数参数意思依次为：列数、行数、要写入的内容、这个Label的样式(可选)
	         Label labelCF = new Label(1, 0, "创建带有字型Formatting的对象", wcfF);
        ws.addCell(labelCF);
	 
	         // (3)创建带有字体颜色Formatting的对象
	         WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10,
	                 WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                 Colour.RED);
         WritableCellFormat wcfFC = new WritableCellFormat(wfc);
	         wcfFC.setAlignment(jxl.format.Alignment.LEFT);//设置水平对齐方式为居中
	         wcfFC.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中 
	        wcfFC.setWrap(true);//设置自动换行
	         Label labelCFC = new Label(2, 0, "创建带有字体颜色Formatting的对象", wcfFC);
	         ws.addCell(labelCFC);
	 
	         // (4)添加Number对象
	         NumberFormat nf = new NumberFormat("#.##");//构造函数的#.##表示保留两位小数
	        WritableCellFormat wcfN = new WritableCellFormat(nf);
        jxl.write.Number labelNF = new jxl.write.Number(1, 1, 3.1415926, wcfN);
	         ws.addCell(labelNF);

	         // (5)添加Boolean对象
         jxl.write.Boolean labelB = new jxl.write.Boolean(0, 2, false);//参数依次为：列数、行数、值
	         ws.addCell(labelB);
	
	        // (6)添加DateTime对象
	         jxl.write.DateTime labelDT = new jxl.write.DateTime(0, 3,
	                 new java.util.Date());
	        ws.addCell(labelDT);
	
	         // (7)添加带有formatting的DateFormat对象
	        jxl.write.DateFormat df = new jxl.write.DateFormat(
	                 "dd MM yyyy hh:mm:ss");//设置日期的格式
	         jxl.write.WritableCellFormat wcfDF = new jxl.write.WritableCellFormat(
	                 df);
	       jxl.write.DateTime labelDTF = new jxl.write.DateTime(1, 3,
	                new java.util.Date(), wcfDF);
	        ws.addCell(labelDTF);

	         //(8)添加图片 只支持PNG格式的图片
	        File image=new File("src//com//office//1.png");
         //前两位是起始格，后两位是图片占多少个格，并非是位置，四个参数的类型都是double，依次是 x, y, width, height,注意，
	//这里的宽和高可不是图片的宽和高，而是图片所要占的单位格的个数
	        WritableImage wimage=new WritableImage(3,6,4,8,image);
	        ws.addImage(wimage);
	         
	         //(10)添加批注
	        Label labelStr=new Label(1,10,"");
	       
	         
	        //合并单元格
	         ws.mergeCells(3, 0, 5, 0);// 合并第一行的第4列到第6列   水平方向
	         ws.mergeCells(3, 1, 3, 5);//合并第4列的的第二行到第六行  垂直方向
	        ws.mergeCells(1, 6, 2, 9);
	        
	        // 设置边框
	       jxl.write.WritableCellFormat wcsB=new jxl.write.WritableCellFormat ();
	        wcsB.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.DOUBLE);
	        wcsB.setBackground(Colour.GREEN);//背景色
	        Label border=new Label(1,4,"边框设置",wcsB);
	        ws.addCell(border);
	        
	        //通过公司求值  暂时还有问题
	        NumberFormat nf1 = new NumberFormat("#");
	//        WritableCellFormat wcfN1 = new WritableCellFormat(nf1);
	 //        jxl.write.Number a = new jxl.write.Number(0, 6, 3, wcfN1);
	 //        jxl.write.Number b = new jxl.write.Number(0, 7,2, wcfN1);
	 //        ws.addCell(a);
	 //        ws.addCell(b);
	 //        Formula l3 = new Formula(0,8,"=SUM(A7:A8)");
 //        WritableCell cell = l3.copyTo(0, 8);
	 //        ws.addCell(cell);
	
	        
	 // 写入excle工作表
	        wwb1.write();
     System.out.println("利用jxl向excle写入数据完成");
	         
	        // 关闭excle工作簿对象
	        wwb1.close();
	
	    }
	}

