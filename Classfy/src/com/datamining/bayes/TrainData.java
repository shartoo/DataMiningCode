package com.datamining.bayes;
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
  //�洢���е��������
  ArrayList<String> allattr=new ArrayList<String>();
  MathLog mymath=new MathLog();
  public HashMap<String,BigDecimal> getEveryAttrPro(){
	  HashMap<String,Integer>  map=new HashMap<String,Integer>();
	  int yesNum=0;    //���շ������Ŀ
	  int noNum=0;
	  try {   
          Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
          Sheet sheet = book.getSheet(0);
          //�Ƚ�������������list�д洢
          allattr.clear();
          System.out.println("贝叶斯预测使用的数据");
          for(int m=0;m<sheet.getColumns();m++){
        	  System.out.print(sheet.getCell(m,0).getContents()+"\t");
        	  allattr.add(sheet.getCell(m,0).getContents());
          }
          //�������������Ԫ��
          for(int i=1;i<sheet.getRows();i++)   //Ĭ�ϵ�һ��Ϊ���Ա���У����������
          {        	
        	  //������ͳ���ļ��е����շ��ࣨ��yes,no�����������
        	if(sheet.getCell(sheet.getColumns()-1,i).getContents().equals(YY)){
        		yesNum++;
        	}
        	else
        		noNum++;
        	//�����ͳ�Ƹ���֧���
         	for(int j=0;j<sheet.getColumns()-1;j++)    //Ĭ�����һ�д����࣬��������ѵ�����ϻ����ڲ��Լ�����
         	  {	    
        	     Cell cell = sheet.getCell(j, i);   //��ȡ��ǰ����������
         		 Cell cell2=sheet.getCell(sheet.getColumns()-1,i);       //��ȡ����У���ѵ��Ԫ�����շ��������һ��
         		 String c2=cell2.getContents().replaceAll("\"", "").trim();
                 String result = cell.getContents().replaceAll("\"", " ").trim();  
               //��ϼ�ֵ��; "age,youth,yes "��ͨ����age,youth,yes/yes����P(age=youth|buy_computer=yes)
                 String data=allattr.get(j)+","+result+","+c2;                      
                 //����ǰ��ֵ������£���ʵ����ֻ�Ǹ�����Num����
                 if(map.containsKey(data)) {
                	 int num=map.get(data);
                	 num++;
                	 map.remove(data);                //�Ǳ������Կ���һ��ɾ��һ�����
                	 map.put(data, num);
                 }
                 //�������
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
	  //����һ�п�ʼ��hashMap�е�value���ᱻ����ĸ��ʴ���
	  System.out.println("贝叶斯预测 map有多少元素"+map.size());
	  HashMap<String,BigDecimal> promap=new HashMap<String,BigDecimal>();  
	   	Set<Map.Entry<String, Integer>> set = map.entrySet();
    	Iterator<Map.Entry<String, Integer>> it=set.iterator();
    	while(it.hasNext()){
    		Map.Entry<String, Integer> entry =(Map.Entry<String,Integer>)it.next();
    		String[] item=entry.getKey().split(",");
    		System.out.println("item长度"+item[item.length-1]);
    		//item�����һ��Ԫ����������࣬����ֱ�ӻ�ȡ
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
    	System.out.println("allattr有多少"+allattr.size());
    	System.out.println("�ж���Ԫ�ر����뵽Map��,train����-----"+promap.size());
	  return promap;
  }
//�������ǵ�ѵ���������
  public void testPro(){
	  HashMap<String,BigDecimal> map=this.getEveryAttrPro();
	  System.out.println("��ȡ��Map�Ĵ�С�ǣ�"+map.size());
	  Set<Map.Entry<String, BigDecimal>> set=map.entrySet();
	  Iterator<Map.Entry<String,BigDecimal>> it=set.iterator();
	  while(it.hasNext()){
		  Map.Entry<String, BigDecimal> entry=(Map.Entry<String, BigDecimal>)it.next();
		  System.out.println("��ȡ�ļ�ֵ�ǣ�"+entry.getKey());
	  }
	  //������д�뵽�ı��ļ�֮ǰ�����
	  try{
	  File f = new File("Data/bayes/testpro.txt");
	  FileWriter fw =  new FileWriter(f);
	  fw.write("");
	  fw.close();
	  }catch (IOException e) {
			e.printStackTrace();
	  }
	  //��ʽ��ʼд��
	  try{
		  BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Data/bayes/testpro.txt"),true));
		  Workbook book = Workbook.getWorkbook(new File("Data/bayes/testpro.xls"));    
          Sheet sheet = book.getSheet(0);
          for(int i=1;i<sheet.getRows()-1;i++){
        	  String str="";
        	  //Excel����ÿһ�еĺ���֤����,�ֱ�Ϊyes��no�ĸ���
        	  BigDecimal proYes=new BigDecimal(1);
        	  BigDecimal proNo=new BigDecimal(1);
        	  for(int j=0;j<sheet.getColumns();j++)
        	  {
        		  String yesKey=allattr.get(j)+","+sheet.getCell(j,i).getContents()+","+YY;
        		  System.out.println("支持率"+yesKey);
        		  BigDecimal Pyes=new BigDecimal(1);
        		  if(map.get(yesKey)!=null)
        		  {
        		  Pyes=map.get(yesKey);
        		  }
        		  System.out.println("��Ӧ��ֵ��"+Pyes);
        		  String noKey=allattr.get(j)+","+sheet.getCell(j,i).getContents()+","+NN;
        		  BigDecimal Pno=new BigDecimal(1);
        		  if(map.get(noKey)!=null){
        			  Pno=map.get(noKey);  
        		  }
        		  //P(X|buy_computer=yes)=P(age=youth|buy_computer)*P(income=medium|buy_computer)*.........
        		  
        		  proYes=proYes.multiply(Pyes);
        		  System.out.println("预测为否的noKey有"+noKey);//������Դ����в����ڴ˹�������û�б����룬Ҳ�������Ϊ0
        		  proNo=proNo.multiply(Pno);
        		  //��֤д�����ԭ�����ı�,ֻ�����������һ�У��б�
        		 if(j==0)
        			str=sheet.getCell(j,i).getContents(); 
        		 else
        		   str=str+","+sheet.getCell(j,i).getContents();
        	  }
        	  //���Ľ����뻹Ҫ�����ѵ������еķ���C1=yes��C2=no�ĸ����������
        	  proYes=proYes.multiply(P_yes);
        	  proNo=proNo.multiply(P_no);
        	  //ÿһ����һ��ʵ��
        	  if(proYes.compareTo(proNo)==1)
        	   {
        	     str=str+","+YY;
        	  }
        	  else if(proYes.compareTo(proNo)==-1){
         	     str=str+","+NN;
        	  }
       	    writer.append(str.replaceAll("\"", ""));   //ȥ��˫���
        	writer.append("\r\n");       //д�뻻��
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
