package bayes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import Genera_decis_tree.PublicData;
public class TrainData {
  public  BigDecimal P_yes=new BigDecimal(0);
  public BigDecimal P_no=new BigDecimal(0); 
  public static final String YY="y";
  public static final String NN="n";
  //存储所有的属性名称
  ArrayList<String> allattr=new ArrayList<String>();
  MathLog mymath=new MathLog();
  public HashMap<String,BigDecimal> getEveryAttrPro(){
	  HashMap<String,Integer>  map=new HashMap<String,Integer>();
	  int yesNum=0;    //最终分类的数目
	  int noNum=0;
	  try {   
          Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
          Sheet sheet = book.getSheet(0);
          //先将所有属性纳入list中存储
          allattr.clear();
          System.out.println("我们先查看有多少个列值");
          for(int m=0;m<sheet.getColumns();m++){
        	  System.out.print(sheet.getCell(m,0).getContents()+"\t");
        	  allattr.add(sheet.getCell(m,0).getContents());
          }
          //在逐个遍历所有元素
          for(int i=1;i<sheet.getRows();i++)   //默认第一行为属性辨别列，不参与计算
          {        	
        	  //先用来统计文件中的最终分类（如yes,no）的先验概率
        	if(sheet.getCell(sheet.getColumns()-1,i).getContents().equals(YY)){
        		yesNum++;
        	}
        	else
        		noNum++;
        	//再逐个统计各分支情况
         	for(int j=0;j<sheet.getColumns()-1;j++)    //默认最后一列代表分类，不管是在训练集合还是在测试集合中
         	  {	    
        	     Cell cell = sheet.getCell(j, i);   //获取当前属性所在列
         		 Cell cell2=sheet.getCell(sheet.getColumns()-1,i);       //获取结果列，即训练元组最终分类类别那一列
         		 String c2=cell2.getContents().replaceAll("\"", "").trim();
                 String result = cell.getContents().replaceAll("\"", " ").trim();  
               //组合键值如; "age,youth,yes "，通过求age,youth,yes/yes来求P(age=youth|buy_computer=yes)
                 String data=allattr.get(j)+","+result+","+c2;                      
                 //如果包含当前键值的则更新，其实我们只是更新了Num而已
                 if(map.containsKey(data)) {
                	 int num=map.get(data);
                	 num++;
                	 map.remove(data);                //非遍历，所以可以一边删除一边添加
                	 map.put(data, num);
                 }
                 //否则加入
                 else if(!(map.containsKey(data))&&data.split(",").length==3){
                	 map.put(data,1);
                 }
         	  }      
          }
          book.close();   
      } catch (Exception e) {   
          e.printStackTrace();   
      }   	
	  P_yes=mymath.getDivide(yesNum, noNum+yesNum);
	  P_no=mymath.getDivide(noNum, yesNum+noNum);
	  //从这一行开始，hashMap中的value将会被具体的概率代替
	  System.out.println("最原始的Map的包含的只是int类型的长度是："+map.size());
	  HashMap<String,BigDecimal> promap=new HashMap<String,BigDecimal>();  
	   	Set<Map.Entry<String, Integer>> set = map.entrySet();
    	Iterator<Map.Entry<String, Integer>> it=set.iterator();
    	while(it.hasNext()){
    		Map.Entry<String, Integer> entry =(Map.Entry<String,Integer>)it.next();
    		String[] item=entry.getKey().split(",");
    		System.out.println("item最后一个分支是什么？？"+item[item.length-1]);
    		//item的最后一个元素总是其分类，可以直接获取
    		if(item[item.length-1].equals(YY)){
    		        BigDecimal pro=mymath.getDivide(entry.getValue(),yesNum);
    		        promap.put(entry.getKey(),pro);
    		        System.out.println("key="+entry.getKey()+"\t"+"value="+pro);
    		}
    		else if(item[item.length-1].equals(NN)){
    			BigDecimal pro=mymath.getDivide(entry.getValue(),noNum);
		        promap.put(entry.getKey(),pro);
		        System.out.println("key="+entry.getKey()+"\t"+"value="+pro);
    		}
    	}
    	System.out.println("allattr的有多少个元素："+allattr.size());
    	System.out.println("有多少元素被加入到Map中,train方法-----"+promap.size());
	  return promap;
  }
//测试我们的训练先验概率
  public void testPro(){
	  HashMap<String,BigDecimal> map=this.getEveryAttrPro();
	  System.out.println("获取的Map的大小是："+map.size());
	  Set<Map.Entry<String, BigDecimal>> set=map.entrySet();
	  Iterator<Map.Entry<String,BigDecimal>> it=set.iterator();
	  while(it.hasNext()){
		  Map.Entry<String, BigDecimal> entry=(Map.Entry<String, BigDecimal>)it.next();
		  System.out.println("获取的键值是："+entry.getKey());
	  }
	  //在我们写入到文本文件之前先清空
	  try{
	  File f = new File("d:/predata/bayes/testpro.txt");
	  FileWriter fw =  new FileWriter(f);
	  fw.write("");
	  fw.close();
	  }catch (IOException e) {
			e.printStackTrace();
	  }
	  //正式开始写入
	  try{
		  BufferedWriter writer = new BufferedWriter(new FileWriter(new File("d:/predata/bayes/testpro.txt"),true));
		  Workbook book = Workbook.getWorkbook(new File("d:/predata/bayes/testpro.xls"));    
          Sheet sheet = book.getSheet(0);
          for(int i=1;i<sheet.getRows()-1;i++){
        	  String str="";
        	  //Excel表中每一行的后验证概率,分别为yes或no的概率
        	  BigDecimal proYes=new BigDecimal(1);
        	  BigDecimal proNo=new BigDecimal(1);
        	  for(int j=0;j<sheet.getColumns();j++)
        	  {
        		  String yesKey=allattr.get(j)+","+sheet.getCell(j,i).getContents()+","+YY;
        		  System.out.println("自己创建的键值是："+yesKey);
        		  BigDecimal Pyes=new BigDecimal(1);
        		  if(map.get(yesKey)!=null)
        		  {
        		  Pyes=map.get(yesKey);
        		  }
        		  System.out.println("对应的值是"+Pyes);
        		  String noKey=allattr.get(j)+","+sheet.getCell(j,i).getContents()+","+NN;
        		  BigDecimal Pno=new BigDecimal(1);
        		  if(map.get(noKey)!=null){
        			  Pno=map.get(noKey);  
        		  }
        		  //P(X|buy_computer=yes)=P(age=youth|buy_computer)*P(income=medium|buy_computer)*.........
        		  
        		  proYes=proYes.multiply(Pyes);
        		  System.out.println("错误的原因是有一个noKey没有了"+noKey);//由于在源数据中不存在此规则，所以没有被加入，也即其概率为0
        		  proNo=proNo.multiply(Pno);
        		  //保证写入的是原来的文本,只不过会加上最后一列，判别
        		 if(j==0)
        			str=sheet.getCell(j,i).getContents(); 
        		 else
        		   str=str+","+sheet.getCell(j,i).getContents();
        	  }
        	  //最后的结果必须还要与整个训练数据中的分类C1=yes，C2=no的各个概率相乘
        	  proYes=proYes.multiply(P_yes);
        	  proNo=proNo.multiply(P_no);
        	  //每一行是一个实例
        	  if(proYes.compareTo(proNo)==1)
        	   {
        	     str=str+","+YY;
        	  }
        	  else if(proYes.compareTo(proNo)==-1){
         	     str=str+","+NN;
        	  }
       	    writer.append(str.replaceAll("\"", ""));   //去掉双引号
        	writer.append("\r\n");       //写入换行
        	 writer.flush();
          }	   
		   
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
  }
  private void catchIOException() {
	// TODO Auto-generated method stub
	
}
public static void main(String[] args){
	  TrainData t=new TrainData();
	  t.testPro();
  }
	
}
